package com.nrifintech.mms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Doctor;

@Repository
public class DoctorDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private List<Doctor> list;
	public List<Doctor> fetchDoctorList() {
		list = this.hibernateTemplate.loadAll(Doctor.class);
		return list;
	}
	
	public Doctor getDoctorById(Integer id) {
		return this.hibernateTemplate.get(Doctor.class, id);
	}
	@Transactional
	public void updateRating(Doctor d, Float rating) {
		d.setRating(rating);;
		this.hibernateTemplate.update(d);
	
	}
	@Transactional
	public void updatepass(Doctor d) {
		d.setPassword(d.getPassword());
		d.setName(d.getName());
		this.hibernateTemplate.update(d);
	}
}
