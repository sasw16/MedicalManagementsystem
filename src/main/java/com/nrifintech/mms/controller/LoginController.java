package com.nrifintech.mms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Doctor;
import com.nrifintech.mms.entity.Patient;
import com.nrifintech.mms.service.AdminService;
import com.nrifintech.mms.service.DoctorService;
import com.nrifintech.mms.service.PatientServiceImpl;

@SessionAttributes(value = { "user" })
@Controller
public class LoginController {
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	@Autowired
	private Patient patient;
	@Autowired
	private Admin admin;
	@Autowired
	private AdminService adminService;
	@Autowired
	private Doctor doctor;
	@Autowired
	private DoctorService doctorService;
//	@RequestMapping(path = "/processlogin", method = RequestMethod.POST)
//	public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password,
//			Model model, ModelMap map, HttpSession session) {
//		ModelAndView m = new ModelAndView("patDash");
//		ModelAndView n = new ModelAndView("index");
//		patient.setEmail(email);
//		patient.setPassword(password);
//		if (this.patientServiceImpl.verifyPatient(patient) == true) {
//			patient = this.patientServiceImpl.getPatient(patient);
//			System.out.println(patient);
//
//			model.addAttribute("user", patient);
//			return m;
//		}
//
//		else {
//			map.put("error", "Invalid login credentials");
//			return n;
//		}
//	}

	@RequestMapping(path = "/processlogin", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("flag") String flag, Model model, ModelMap map, HttpSession session) {

		if (flag.equals("patient")) {
			patient.setEmail(email);
			patient.setPassword(password);

			if (this.patientServiceImpl.verifyPatient(patient) == true) {
				patient = this.patientServiceImpl.getPatient(patient);
				System.out.println(patient);
				model.addAttribute("user", patient);
				return "redirect:patdash";
			}
			map.put("error", "true");
			return "index";

		}
		if (flag.equals("admin")) {
			admin.setA_id(Integer.parseInt(email));
			admin.setPassword(password);
			if (this.adminService.verifyAdmin(admin) == true) {
				admin = this.adminService.getAdminById(admin.getA_id());
				System.out.println(admin);
				model.addAttribute("user", admin);
				return "redirect:admindashboard";
			} else {
				map.put("error", "true");
				return "adminlogin";
			}
		}
		if (flag.equals("doctor")) {
			doctor.setEmail(email);
			doctor.setPassword(password);

			if (this.doctorService.verifyDoctor(doctor)) {
				doctor = this.doctorService.getDoctor(doctor);
				System.out.println(doctor);
				model.addAttribute("user", doctor);
				return "redirect:doctordashboard";
			} else {
				map.put("error", "true");
				return "doctorlogin";
			}
		}

		else {
			return "";
		}
	}
}
