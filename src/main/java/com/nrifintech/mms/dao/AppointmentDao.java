package com.nrifintech.mms.dao;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nrifintech.mms.entity.Appointment;

@Repository
public class AppointmentDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private List<Appointment> list;
	
	@Transactional
	public int registerAppointment(Appointment a) {
		return (Integer)this.hibernateTemplate.save(a);
	}
	
	public List<Appointment> fetchAppointmentList(){
		list = this.hibernateTemplate.loadAll(Appointment.class);
		return list;
	}
	public Appointment getAppointmentById(Integer id) {
		return this.hibernateTemplate.get(Appointment.class, id);
	}
	@Transactional
	public void cancelAppointment(Appointment a) {
		a.setStatus("Inactive");
		this.hibernateTemplate.update(a);
	}
	@Transactional
	public void cancelAppointmentTrial(Appointment a) {
		a.setStatus("Inactive");
		a.setCancel("Cancelled By Patient");
		this.hibernateTemplate.update(a);
	}
	@Transactional
	public void updateReview(Appointment a, Integer review) {
		a.setReview(review);;
		this.hibernateTemplate.update(a);
	}
}
