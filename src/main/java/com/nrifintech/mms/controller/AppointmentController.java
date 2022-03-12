package com.nrifintech.mms.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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

import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.entity.Doctor;
import com.nrifintech.mms.entity.Patient;
import com.nrifintech.mms.service.AppointmentService;
import com.nrifintech.mms.service.DoctorService;

@SessionAttributes(value = { "user" })
@Controller
public class AppointmentController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private Doctor doctor;
	@Autowired
	private Appointment appointment;
	@Autowired
	private AppointmentService appointmentService;

	private static List<String> listOfSlots = Arrays.asList("10.00", "10.15", "10.30", "10.45", "11.00", "11.15",
			"11.30", "11.45", "12.00", "12.15", "12.30", "12.45", "14.00", "14.15", "14.30", "14.45", "15.00", "15.15",
			"15.30", "15.45", "16.00", "16.15", "16.30", "16.45");

	@RequestMapping("/cancelappointment")
	public ModelAndView cancelAppointment(@RequestParam("ap_id") Integer ap_id, ModelMap map, Model model,
			HttpSession session) {
		boolean check = this.appointmentService.checkForCancel(ap_id);
		ModelAndView m = new ModelAndView("patDash");
		if (check == true) {

			Patient p = (Patient) session.getAttribute("user");
			List<Appointment> appointmentByPId = this.appointmentService.getAppointmentByPId(p.getPid());
			model.addAttribute("allAppointments", appointmentByPId);
			map.put("success", "true");
			return m;
		}
		Patient p = (Patient) session.getAttribute("user");
		List<Appointment> appointmentByPId = this.appointmentService.getAppointmentByPId(p.getPid());
		model.addAttribute("allAppointments", appointmentByPId);
		map.put("error", "true");
		return m;
	}

	@RequestMapping("/filterdoctor")
	public ModelAndView filterDoctor(@RequestParam("specialisation") String specialisation, Model model) {
		ModelAndView m = new ModelAndView("addappointment");
		List<Doctor> filtered = this.doctorService.filterDoctorForPatient(specialisation);
		model.addAttribute("allDoctors", filtered);

		return m;
	}

	@RequestMapping("/filterdoctorname")
	public ModelAndView filterDoctorName(@RequestParam("filteredname") String filteredname, Model model) {
		ModelAndView m = new ModelAndView("addappointment");
		List<Doctor> filterednames = this.doctorService.filterDoctorForPatientName(filteredname);
		model.addAttribute("allDoctors", filterednames);

		return m;
	}

	@RequestMapping("/bookappointment")
	public ModelAndView appointmentForm(@RequestParam("doc_id") Integer doc_id, HttpSession session, Model model,
			ModelMap map) {
		ModelAndView m = new ModelAndView("bookappointment");
		doctor = this.doctorService.getDoctorById(doc_id);
		// Patient p = (Patient)session.getAttribute("user");
		model.addAttribute("doctor", doctor);
		return m;
	}

	@RequestMapping("/processappointment")
	public ModelAndView processAppointment(@RequestParam("doc_id") Integer doc_id, @RequestParam("slot") String slot,
			@RequestParam("date") String date, HttpSession session, Model model, ModelMap map) {
		ModelAndView m = new ModelAndView("confirm");
		doctor = this.doctorService.getDoctorById(doc_id);
		Patient p = (Patient) session.getAttribute("user");
		System.out.println(doctor);
		System.out.println(p);
		appointment.setDate(date);
		appointment.setSlot(slot);
		appointment.setStatus("Active");
		appointment.setCancel("Not Cancelled");
		appointment.setDoc_id(doc_id);
		appointment.setSpecialisation(doctor.getSpecialisation());
		appointment.setP_id(p.getPid());
		appointment.setDoctorName(doctor.getName());
		appointment.setPatientName(p.getName());
		appointment.setFees(doctor.getFees());
		if (this.appointmentService.insertAppointment(appointment) == true) {
			model.addAttribute("show", "false");
			map.put("success", "true");
			model.addAttribute("bookanother", "true");
			return m;
		}
		model.addAttribute("show", "true");
		map.put("error", "true");
		List<String> notAvailable = this.appointmentService.slotList(doc_id, date);
		model.addAttribute("listOfSlots", listOfSlots);
		model.addAttribute("notAvailable", notAvailable);

		model.addAttribute("doctor", doctor);
		model.addAttribute("date", date);
		model.addAttribute("show", "true");
		return m;
	}

	@PostMapping("/processdate")
	public ModelAndView displaySlots(@RequestParam("doc_id") Integer id, @RequestParam("date") String date, Model model,
			ModelMap map) {
		ModelAndView m = new ModelAndView("bookappointment");

		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		LocalDate d = LocalDate.parse(date);
		System.out.println(d);

		long days = ChronoUnit.DAYS.between(ld, d);
		System.out.println(days);

		if (days <= 0 || days > 7) {
			map.put("error", "true");

			model.addAttribute("doctor", doctor);
			return m;
		}
		ModelAndView confirm = new ModelAndView("confirm");

		doctor = this.doctorService.getDoctorById(id);
		List<String> notAvailable = this.appointmentService.slotList(id, date);
		model.addAttribute("listOfSlots", listOfSlots);
		model.addAttribute("notAvailable", notAvailable);

		model.addAttribute("doctor", doctor);
		model.addAttribute("date", date);
		model.addAttribute("show", "true");

		return confirm;
	}

}