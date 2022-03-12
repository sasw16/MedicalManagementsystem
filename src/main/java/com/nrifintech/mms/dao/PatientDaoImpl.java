package com.nrifintech.mms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nrifintech.mms.entity.Patient;




@Repository
public class PatientDaoImpl {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private List<Patient> list; 
	@Autowired
	private Patient patient;
	@Transactional
	public int register(Patient patient) {
		int id = (Integer) this.hibernateTemplate.save(patient);
		return id;
	}

	public List<Patient> getAll(){
		list = this.hibernateTemplate.loadAll(Patient.class);
		return list;
	}

	public Patient getPatientById(Integer id) {
		patient = this.hibernateTemplate.get(Patient.class, id);
		return patient;
	}
	@Transactional
	public void updatePatient(Patient patient) {
		patient.setPhone(patient.getPhone());
		patient.setAddress(patient.getAddress());
		patient.setEmail(patient.getEmail());
		patient.setPassword(patient.getPassword());
		this.hibernateTemplate.update(patient);
	}
	
	
	
}
