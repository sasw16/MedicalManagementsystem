package com.nrifintech.mms.util;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.service.DoctorService;

public class DoctorPDFExporter {

	private List<Appointment> appointments;
	private boolean isMonthly;
	private DoctorService doctorService;

	public DoctorPDFExporter(List<Appointment> appointments, boolean isMonthly) {
		this.appointments = appointments;
		this.isMonthly = isMonthly;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(6);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.WHITE);
		font.setSize(11);

		cell.setPhrase(new Phrase("Appt. ID", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Slot", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Patient ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Patient Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Status", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		font.setSize(11);

		for (Appointment ap : appointments) {
			cell.setPhrase(new Phrase(Integer.toString(ap.getAp_id()), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ap.getDate(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ap.getSlot(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(Integer.toString(ap.getP_id()), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ap.getPatientName(), font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(ap.getStatus()));
			table.addCell(cell);
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {

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

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(15);
		font.setColor(Color.BLACK);

		Paragraph p;

		if (appointments == null || appointments.size() == 0) {

			p = new Paragraph("No appointment records found for selected doctor", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

		} else {
			Appointment ap1 = appointments.get(0);
			if (isMonthly)
				p = new Paragraph("Monthly Appointment Details of Doctor", font);
			else
				p = new Paragraph("Weekly Appointment Details of Doctor", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			String intro = "\nDoctor ID: " + ap1.getDoc_id() + "\n";
			intro += "Doctor Name: " + ap1.getDoctorName() + "\n";
			intro += "Specialisation: " + ap1.getSpecialisation() + "\n";
			intro += "Fees: Rs " + ap1.getFees() + "\n";
			intro += "Appointments from " + firstDay.toString() + " to " + lastDay.toString() + "\n\n";

			font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setSize(12);
			font.setColor(Color.BLACK);
			p = new Paragraph(intro, font);

			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] { 1.5f, 1.8f, 1.5f, 1.7f, 4.0f, 1.5f });
			table.setSpacingBefore(10);

			writeTableHeader(table);
			writeTableData(table);

			document.add(table);
		}

		document.close();

	}

}
