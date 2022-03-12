
package com.nrifintech.mms.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.mms.dao.PatientDaoImpl;
import com.nrifintech.mms.entity.Patient;

@Service
public class PatientServiceImpl {
	@Autowired
	private PatientDaoImpl patientDaoImpl;
	@Autowired
	private Patient patient;

	private List<Patient> list;

	public int register(Patient newPatient) {
		List<Patient> patientList = this.patientDaoImpl.getAll();
		boolean isPresent = false;
		for (Patient patient : patientList) {
			if ((patient.getEmail().equals(newPatient.getEmail())))
				isPresent = true;

		}
		if (isPresent == true) {
			return -1;
		}
		newPatient.setPassword(Base64.getEncoder().encodeToString(newPatient.getPassword().getBytes()));
		return this.patientDaoImpl.register(newPatient);
	}

	public boolean verifyPatient(Patient p) {
		list = this.patientDaoImpl.getAll();
		for (Patient pr : list) {
			byte[] actualByte = Base64.getDecoder().decode(pr.getPassword());
			String actualString = new String(actualByte);

			if (pr.getEmail().equals(p.getEmail()) && actualString.equals(p.getPassword())) {
				// patient = this.patientDaoImpl.getPatientById(pr.getPid());

				return true;
			}
		}
		return false;
	}

	public Patient getPatient(Patient p) {
		list = this.patientDaoImpl.getAll();
		for (Patient pr : list) {
			byte[] actualByte = Base64.getDecoder().decode(pr.getPassword());
			String actualString = new String(actualByte);
			if (pr.getEmail().equals(p.getEmail()) && actualString.equals(p.getPassword())) {
				patient = this.patientDaoImpl.getPatientById(pr.getPid());
			}
		}
		return patient;
	}

	public void updatePatient(Patient patient) {
		patient.setPassword(Base64.getEncoder().encodeToString(patient.getPassword().getBytes()));
		this.patientDaoImpl.updatePatient(patient);
	}

}
