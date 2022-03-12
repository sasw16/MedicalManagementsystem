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

import com.nrifintech.mms.dao.AppointmentDao;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.entity.Doctor;
import com.nrifintech.mms.entity.Patient;
import com.nrifintech.mms.service.AppointmentService;
import com.nrifintech.mms.service.DoctorService;
import com.nrifintech.mms.service.PatientServiceImpl;

@Controller
@SessionAttributes(value = { "user" })
public class PatientController {

	@Autowired
	private Patient patient;
	@Autowired
	private Appointment appointment;
	@Autowired
	private PatientServiceImpl patientServiceImpl;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private AppointmentDao appointmentDao;

	@RequestMapping("/addappointment")
	public ModelAndView addAppointment(HttpSession session) {
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return new ModelAndView("addappointment");
	}

	@RequestMapping("/patientprofile")
	public ModelAndView patientProfile(HttpSession session) {
		ModelAndView m = new ModelAndView("patientprofile");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return m;
	}

	@RequestMapping("/doctorlist")
	public ModelAndView doctorList(HttpSession session, Model model) {
		ModelAndView m = new ModelAndView("doctorlist");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		List<Doctor> lists = this.doctorService.getDoctorListForPatients();
		model.addAttribute("allDoctors", lists);

		return m;
	}

	@RequestMapping("/patdash")
	public ModelAndView patientDashboard(HttpSession session, Model model) {
		ModelAndView m = new ModelAndView("patDash");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		Patient p = (Patient) session.getAttribute("user");
		List<Appointment> appointmentByPId = this.appointmentService.getAppointmentByPId(p.getPid());
		// System.out.println("first:"+appointmentByPId.get(0));
		List<Appointment> appointmentReview = this.appointmentService.getAppointmentReview(p.getPid());
		System.out.println(appointmentReview.size());
		if (appointmentReview.size() > 0) {
			if (appointmentReview.get(0).getReview() == 0) {
				System.out.println("Id: " + appointmentReview.get(0).getAp_id());
				model.addAttribute("message", 1);
				model.addAttribute("reviewappointment", appointmentReview.get(0));

			}

		}

		model.addAttribute("allAppointments", appointmentByPId);
		return m;
	}

	@PostMapping("/editreview")
	public ModelAndView editReview(@RequestParam("ap_id") Integer ap_id, @RequestParam("review") Integer review,
			Model model, ModelMap map, HttpSession session) {
		ModelAndView m = new ModelAndView("patDash");
		Patient p = (Patient) session.getAttribute("user");
		List<Appointment> appointmentByPId = this.appointmentService.getAppointmentByPId(p.getPid());
		appointment = this.appointmentDao.getAppointmentById(ap_id);
		this.appointmentService.updateReview(appointment, review);
		this.doctorService.updateRating();
		model.addAttribute("allAppointments", appointmentByPId);
		return m;
	}

	@RequestMapping("/editpatient")
	public ModelAndView editPatient(HttpSession session) {
		ModelAndView m = new ModelAndView("editpatient");
		if (session.getAttribute("user") == null)
			return new ModelAndView("index");
		return m;
	}

	@PostMapping("/editpatientdetails")
	public ModelAndView editPatient(@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("newpassword") String newpassword, Model model,
			ModelMap map, HttpSession session) {

		ModelAndView m = new ModelAndView("editpatient");
		Patient p = (Patient) session.getAttribute("user");
		byte[] actualByte = Base64.getDecoder().decode(p.getPassword());
		String decodedPass = new String(actualByte);

		if (password.equals(decodedPass) && newpassword != "") {
			patient.setPid(p.getPid());
			patient.setName(name);
			patient.setEmail(email);
			patient.setPassword(newpassword);
			patient.setAddress(address);
			patient.setAge(p.getAge());
			patient.setGender(p.getGender());
			patient.setPhone(phone);

			System.out.println(patient);

			this.patientServiceImpl.updatePatient(patient);
			map.put("success", "true");
			model.addAttribute("user", patient);
			return m;
		}
		map.put("error", "true");
		return m;

	}

}
