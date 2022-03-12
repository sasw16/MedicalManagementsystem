package com.nrifintech.mms.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nrifintech.mms.entity.Appointment;

public class DoctorExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Appointment> appointments;
	private boolean isMonthly;

	public DoctorExcelExporter(List<Appointment> appointments, boolean isMonthly) {
		this.appointments = appointments;
		this.isMonthly = isMonthly;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {

		Appointment ap1 = appointments.get(0);

		DateUtil dateUtil = new DateUtil();
		LocalDate firstDay;
		LocalDate lastDay;

		if (isMonthly) {
			firstDay = dateUtil.getFirstDayOfMonth();
			lastDay = dateUtil.getLastDayOfMonth();
		} else {
			firstDay = dateUtil.getFirstDayOfWeek();
			lastDay = dateUtil.getLastDayOfWeek();
		}

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:F2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A3:F3"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A4:F4"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A5:F5"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("A6:F6"));

		Row row = sheet.createRow(0);
		if (isMonthly)
			createCell(row, 0, "Monthly Appointment Details of Doctor", style);
		else
			createCell(row, 0, "Weekly Appointment Details of Doctor", style);

		font.setFontHeight(14);
		style.setFont(font);

		row = sheet.createRow(1);
		createCell(row, 0, "Doctor ID: " + ap1.getDoc_id(), style);

		row = sheet.createRow(2);
		createCell(row, 0, "Doctor Name: " + ap1.getDoctorName(), style);

		row = sheet.createRow(3);
		createCell(row, 0, "Specialisation: " + ap1.getSpecialisation(), style);

		row = sheet.createRow(4);
		createCell(row, 0, "Fees: Rs " + ap1.getFees(), style);

		row = sheet.createRow(5);
		createCell(row, 0, "Appointments from " + firstDay.toString() + " to " + lastDay.toString(), style);

		row = sheet.createRow(6);

		row = sheet.createRow(7);
		createCell(row, 0, "Appointment ID", style);
		createCell(row, 1, "Date", style);
		createCell(row, 2, "Slot", style);
		createCell(row, 3, "Patient ID", style);
		createCell(row, 4, "Patient Name", style);
		createCell(row, 5, "Status", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 8;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		font.setBold(false);
		style.setFont(font);

		for (Appointment appointment : appointments) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, appointment.getAp_id(), style);
			createCell(row, columnCount++, appointment.getDate(), style);
			createCell(row, columnCount++, appointment.getSlot(), style);
			createCell(row, columnCount++, appointment.getP_id(), style);
			createCell(row, columnCount++, appointment.getPatientName(), style);
			createCell(row, columnCount++, appointment.getStatus(), style);

		}

	}

	public void export(HttpServletResponse response) throws IOException {
		sheet = workbook.createSheet("Doctors");
		if (appointments == null || appointments.size() == 0) {
			CellStyle style = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontHeight(16);
			style.setFont(font);
			style.setAlignment(HorizontalAlignment.CENTER);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:I1"));
			Row row = sheet.createRow(0);
			createCell(row, 0, "No appointment records found for selected doctor", style);
		} else {
			writeHeaderLine();
			writeDataLines();
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}

}
