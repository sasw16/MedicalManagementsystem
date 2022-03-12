package com.nrifintech.mms.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.mms.dao.AppointmentDao;
import com.nrifintech.mms.dao.DoctorDao;
import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.entity.Doctor;

@Service
public class DoctorService {

	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private Doctor doctor;

	private List<Doctor> list;

	public boolean verifyDoctor(Doctor d) {
		list = this.doctorDao.fetchDoctorList();
		for (Doctor doc : list) {
			byte[] actualByte = Base64.getDecoder().decode(doc.getPassword());
			String actualString = new String(actualByte);
			if (doc.getEmail().equalsIgnoreCase(d.getEmail()) && actualString.equals(d.getPassword())) {
				return true;
			}
		}

		return false;
	}

	public Doctor getDoctor(Doctor d) {
		list = this.doctorDao.fetchDoctorList();
		for (Doctor doc : list) {
			byte[] actualByte = Base64.getDecoder().decode(doc.getPassword());
			String actualString = new String(actualByte);
			if (doc.getEmail().equalsIgnoreCase(d.getEmail()) && actualString.equals(d.getPassword())) {
				doctor = this.doctorDao.getDoctorById(doc.getDoc_id());
			}
		}
		return doctor;
	}

	public List<Doctor> filterDoctorForPatient(String spcl) {
		list = this.doctorDao.fetchDoctorList();
		List<Doctor> filtered = new ArrayList<>();
		for (Doctor doc : list) {

			if (doc.getSpecialisation().equals(spcl) && doc.getStatus().equals("Active")) {
				if (doc.getImage() == null || doc.getImage().length == 0)
					doc.setBase64Image(null);
				else
					doc.setBase64Image(Base64.getEncoder().encodeToString(doc.getImage()));
				filtered.add(doc);
			}
		}

		return filtered;
	}

	public List<Doctor> filterDoctorForPatientName(String name) {
		list = this.doctorDao.fetchDoctorList();
		List<Doctor> filterednames = new ArrayList<>();
		for (Doctor doc : list) {

			if (doc.getName().toLowerCase().contains(name.toLowerCase()) && doc.getStatus().equals("Active")) {
				if (doc.getImage() == null || doc.getImage().length == 0)
					doc.setBase64Image(null);
				else
					doc.setBase64Image(Base64.getEncoder().encodeToString(doc.getImage()));
				filterednames.add(doc);
			}
		}

		return filterednames;
	}

	public Doctor getDoctorById(Integer id) {
		return this.doctorDao.getDoctorById(id);
	}

	public List<Doctor> getDoctorListForAdmin() {

		list = this.doctorDao.fetchDoctorList();

		return list;
	}

	public void updateRating() {
		List<Doctor> lists = this.getDoctorListForPatients();
		List<Appointment> ls = this.appointmentDao.fetchAppointmentList();
		for (Doctor d : lists) {
			int p = 0;
			int count = 0;
			float av;

			for (Appointment ap : ls) {
				if (d.getDoc_id() == ap.getDoc_id() && ap.getReview() != 0) {
					p = p + ap.getReview();
					count++;

				}

			}
			if (p > 0 && count > 0) {
				av = (float) p / count;
				this.doctorDao.updateRating(d, av);
			}

		}

	}

	public List<Doctor> getDoctorListForPatients() {
		List<Doctor> doctors = new ArrayList<>();
		list = this.doctorDao.fetchDoctorList();
		for (Doctor d : list)
			if (d.getStatus().equals("Active")) {
				if (d.getImage() == null || d.getImage().length == 0)
					d.setBase64Image(null);
				else
					d.setBase64Image(Base64.getEncoder().encodeToString(d.getImage()));
				doctors.add(d);
			}
		return doctors;
	}

	public void updatepass(Doctor p) {
		// TODO Auto-generated method stub
		this.doctorDao.updatepass(p);
	}
}
