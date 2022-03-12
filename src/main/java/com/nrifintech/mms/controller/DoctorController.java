package com.nrifintech.mms.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.entity.Doctor;
import com.nrifintech.mms.service.AppointmentService;
import com.nrifintech.mms.service.DoctorService;

@SessionAttributes(value = {"user"})
@Controller
public class DoctorController {
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping("/doctordashboard")
	public ModelAndView patientDashboard(HttpSession session,Model model) {
		ModelAndView m = new ModelAndView("doctordashboard");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		Doctor d = (Doctor)session.getAttribute("user");
		List<Appointment> ap_list = this.appointmentService.getAppointmentByDId(d.getDoc_id());
		model.addAttribute("allAppointments", ap_list);
		return m;
	}
	@RequestMapping("/doctorprofile")
	public ModelAndView patientProfile(HttpSession session) {
		ModelAndView m = new ModelAndView("doctorprofile");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return m;
	}
	
	@RequestMapping("/changepassworddoctor")
	public ModelAndView editPatient(HttpSession session) {
		ModelAndView m = new ModelAndView("changepassworddoctor");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return m;
	}
	
	@PostMapping("/editdoctorpassword")
	public ModelAndView editDoctor(@RequestParam("password") String password,
			@RequestParam("newpassword") String newpassword, Model model, ModelMap map, HttpSession session) {

		ModelAndView m = new ModelAndView("changepassworddoctor");
		Doctor d = (Doctor) session.getAttribute("user");
		System.out.println(d);
		byte[] actualByte = Base64.getDecoder().decode(d.getPassword());
		String actualString = new String(actualByte);

		if (password.equals(actualString) && newpassword != "") {

			d.setPassword(Base64.getEncoder().encodeToString(newpassword.getBytes()));

			this.doctorService.updatepass(d);
			System.out.println(d);
			map.put("success", "true");
			model.addAttribute("user", d);
			return m;
		}
		map.put("error", "true");
		return m;

	}
	
}
