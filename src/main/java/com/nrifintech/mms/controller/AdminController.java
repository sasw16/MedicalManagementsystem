package com.nrifintech.mms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.entity.Doctor;
import com.nrifintech.mms.service.AdminService;
import com.nrifintech.mms.service.AppointmentService;
import com.nrifintech.mms.service.DoctorService;
import com.nrifintech.mms.util.DoctorExcelExporter;
import com.nrifintech.mms.util.DoctorPDFExporter;

@SessionAttributes(value = { "user" })
@Controller
public class AdminController {

	@Autowired
	private Admin admin;

	@Autowired
	private AdminService adminService;

	@Autowired
	private Doctor doctor;
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping("/admindashboard")
	public ModelAndView patientDashboard(HttpSession session, Model model) {
		ModelAndView m = new ModelAndView("admindashboard");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
		model.addAttribute("allDoctors", doctorList);
		return m;
	}

	@RequestMapping("/changepassword")
	public ModelAndView editPatient(HttpSession session) {
		ModelAndView m = new ModelAndView("changepassword");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return m;
	}

	@PostMapping("/editadminpassword")
	public ModelAndView editPatient(@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword, Model model, ModelMap map, HttpSession session) {

		ModelAndView m = new ModelAndView("changepassword");
		Admin p = (Admin) session.getAttribute("user");
		System.out.println(p);
		byte[] actualByte = Base64.getDecoder().decode(p.getPassword());
		String actualString = new String(actualByte);

		if (password.equals(actualString) && newpassword != "") {

			p.setPassword(Base64.getEncoder().encodeToString(newpassword.getBytes()));

			this.adminService.updatepass(p);
			System.out.println(p);
			map.put("success", "true");
			model.addAttribute("user", p);
			return m;
		}
		map.put("error", "true");
		return m;

	}

	@RequestMapping("/addadmin")
	public ModelAndView addadmin(HttpSession session) {
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return new ModelAndView("addadmin");
	}

	@RequestMapping("/adddoctor")
	public ModelAndView adddoctor(HttpSession session) {
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return new ModelAndView("adddoctor");
	}

	@PostMapping("/addnewadmin")
	public ModelAndView addnewAdmin(@RequestParam("email") String username, @RequestParam("gender") String gender,
			@RequestParam("admin_password") String adminPassword, @RequestParam("password") String password,
			@RequestParam("phone") String phone, ModelMap map, HttpSession session, Model model) {
		ModelAndView m = new ModelAndView("addadmin");
		Admin a = (Admin) session.getAttribute("user");
		byte[] actualByte = Base64.getDecoder().decode(a.getPassword());
		String decodedAdminPass = new String(actualByte);
		if (!decodedAdminPass.equals(adminPassword)) {
			map.put("error", "true");
			map.put("message", "Incorrect password");
			return m;
		}
		admin.setUserName(username);
		admin.setPassword(password);
		admin.setGender(gender);
		admin.setPhone(phone);
		int id = this.adminService.addAdmin(admin);
		System.out.println(id);
		if (id > -1) {
			map.put("success", "true");
			map.put("addedAdmin", "true");
			map.put("newAdminId", id);
			model.addAttribute("id", admin.getA_id());
			m = new ModelAndView("admindashboard");
			List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
			model.addAttribute("allDoctors", doctorList);
			return m;
		}
		map.put("error", "true");
		map.put("message", "Admin with same name already exists");
		return m;
	}

	@PostMapping("/addnewdoctor")
	public ModelAndView addnewDoctor(@RequestParam("user_id") Integer id, @RequestParam("name") String name,
			@RequestParam("age") int age, @RequestParam("email") String email,
			@RequestParam("specialisation") String specialisation, @RequestParam("gender") String gender,
			@RequestParam("password") String password, @RequestParam("fees") String fees,
			@RequestParam("phone") String phone, @RequestParam("image") MultipartFile file, ModelMap map,
			HttpSession session, Model model) {
		ModelAndView m = new ModelAndView("adddoctor");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		doctor.setAddedBy(Integer.toString(id));
		doctor.setEmail(email);
		doctor.setFees(fees);
		if (name.length() > 2 && name.substring(0, 2).equals("Dr")) {
			doctor.setName(name);
		} else {
			name = "Dr. " + name;
			doctor.setName(name);
		}
		doctor.setPassword(password);
		doctor.setSpecialisation(specialisation);
		doctor.setStatus("Active");
		doctor.setAge(age);
		doctor.setGender(gender);
		doctor.setPhone(phone);
		if (this.adminService.addDoctor(doctor, file) != true) {
			map.put("error", "true");
			return m;
		}
		map.put("success", "true");
		map.put("addedDoctor", "true");
		m = new ModelAndView("admindashboard");
		List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
		model.addAttribute("allDoctors", doctorList);
		return m;
	}

	@RequestMapping("/enableDoctor")
	public String enableDoctor(@RequestParam("doc_id") Integer doc_id, Model model, ModelMap map) {
		boolean check = this.adminService.checkAndEnable(doc_id);
		if (check) {
			List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
			model.addAttribute("allDoctors", doctorList);
			map.put("success1", "true");
			return "admindashboard";
		}
		List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
		model.addAttribute("allDoctors", doctorList);
		map.put("error1", "true");
		return "admindashboard";
	}

	@RequestMapping("/disableDoctor")
	public String disableDoctor(@RequestParam("doc_id") Integer doc_id, Model model, ModelMap map) {
		boolean check = this.adminService.checkAndDisable(doc_id);
		if (check) {
			List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
			model.addAttribute("allDoctors", doctorList);
			map.put("disableSuccess", "true");
			return "admindashboard";
		}
		List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
		model.addAttribute("allDoctors", doctorList);
		map.put("error", "true");
		return "admindashboard";
	}

	@RequestMapping("/editdoctor")
	public ModelAndView editDoctor(Model model) {
		ModelAndView m = new ModelAndView("editdoctor");
		List<Doctor> doctorList = this.doctorService.getDoctorListForAdmin();
		model.addAttribute("allDoctors", doctorList);
		return m;
	}

	@RequestMapping("/editform")
	public ModelAndView editForm(@RequestParam("doc_id") Integer doc_id, Model model) {
		ModelAndView m = new ModelAndView();
		Doctor d = this.doctorService.getDoctorById(doc_id);
		model.addAttribute("doctor", d);
		return m;
	}

	@PostMapping("/editdoctorfees")
	public ModelAndView editDetails(@RequestParam("name") String name, @RequestParam("fees") String fees,
			@RequestParam("doc_id") Integer doc_id, Model model) {

		ModelAndView m = new ModelAndView("editform");
		doctor = this.doctorService.getDoctorById(doc_id);
		this.adminService.updateFees(doctor, fees);
		model.addAttribute("success", "Updated fees");
		return m;
	}

	@RequestMapping("/report")
	public ModelAndView report(Model model) {
		ModelAndView m = new ModelAndView("report");
		List<Doctor> allDoctors;
		try {
			allDoctors = this.doctorService.getDoctorListForAdmin();
			for (Doctor d : allDoctors) {
				if (!d.getName().substring(0, 2).equalsIgnoreCase("dr")) {
					d.setName("Dr. " + d.getName());
				}
			}
		} catch (Exception e) {
			allDoctors = null;
		}

		model.addAttribute("allDoctors", allDoctors);
		return m;
	}

	@RequestMapping(path = "/exportdoctors", method = RequestMethod.POST)
	public void exportDoctors(@RequestParam("doc_id") Integer doc_id, @RequestParam("interval") String interval,
			@RequestParam("type") String type, HttpServletResponse response) {

		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = null;

		List<Appointment> appointments = null;
		DoctorExcelExporter excelExporter;
		DoctorPDFExporter pdfExporter;
		Boolean isMonthly = false;

		try {

			if (interval.equals("Weekly")) {
				appointments = this.appointmentService.getWeeklyAppointmentsByDocID(doc_id);
			} else if (interval.equals("Monthly")) {
				appointments = this.appointmentService.getMonthlyAppointmentsByDocID(doc_id);
				isMonthly = true;
			}
			if (type.equals("Excel")) {
				headerValue = "attachment; filename=doctor_" + doc_id + "_" + currentDateTime + ".xlsx";
				response.setHeader(headerKey, headerValue);
				excelExporter = new DoctorExcelExporter(appointments, isMonthly);
				excelExporter.export(response);
			} else if (type.equals("PDF")) {
				headerValue = "attachment; filename=doctor_" + doc_id + "_" + currentDateTime + ".pdf";
				response.setHeader(headerKey, headerValue);
				pdfExporter = new DoctorPDFExporter(appointments, isMonthly);
				pdfExporter.export(response);
			}

		} catch (Exception e) {
			System.out.println("Error exporting excel");
			e.printStackTrace();
		}

	}

}
