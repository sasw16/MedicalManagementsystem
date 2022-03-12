package com.nrifintech.mms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nrifintech.mms.entity.Patient;
import com.nrifintech.mms.service.PatientServiceImpl;

@Controller
public class HomeController {
	@Autowired
	private Patient patient;
	
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView("index");
		return m;
	}
	
	@RequestMapping("/register")
	public ModelAndView registerPage() {
		 return new ModelAndView("register");
	}
	
	@RequestMapping("/admin")
	public ModelAndView adminlogin() {
		return new ModelAndView("adminlogin");
	}
	@RequestMapping("/doctor")
	public ModelAndView doctorlogin() {
		return new ModelAndView("doctorlogin");
	}
	
	@RequestMapping(path = "/registerpatient", method = RequestMethod.POST)
	public ModelAndView registerPatient(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("address") String address, @RequestParam("gender") String gender,
			@RequestParam("age") String age, @RequestParam("password") String password, Model model, ModelMap map) {
		ModelAndView m = new ModelAndView("index");
		ModelAndView n = new ModelAndView("register");
		
		patient.setName(name);
		patient.setEmail(email);
		patient.setPhone(phone);
		patient.setAddress(address);
		patient.setPassword(password);
		patient.setGender(gender);
		patient.setAge(age);
		
		int check = this.patientServiceImpl.register(patient);
		if(check!=-1)
			return m;
		else {
			map.put("error", "true");
			return n;
		}
	}
}
