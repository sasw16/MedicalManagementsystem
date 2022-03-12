package com.nrifintech.mms.dao;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nrifintech.mms.entity.Admin;
import com.nrifintech.mms.entity.Doctor;


@Repository
public class AdminDao {


	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private List<Admin> list;
	private List<Doctor> li;
	
	@Transactional
	public int register(Admin admin) {
		int id = (Integer) this.hibernateTemplate.save(admin);
		return id;
	}
	
	
	public List<Admin> getAll(){
		list = this.hibernateTemplate.loadAll(Admin.class);
		return list;
	}
	public Admin getAdminById(Integer id) {
		return this.hibernateTemplate.get(Admin.class, id);
	}
	
	public List<Doctor> getAllDoctors() {
		li = this.hibernateTemplate.loadAll(Doctor.class);
		return li;
	}
	
	@Transactional
	public int addNewDoctor(Doctor d) {
		return (Integer)this.hibernateTemplate.save(d);
	}
	
	@Transactional
	public void checkAndDisable(Doctor d) {
		d.setStatus("Inactive");
		this.hibernateTemplate.update(d);
	}
	
	@Transactional
	public void checkAndEnable(Doctor d) {
		d.setStatus("Active");
		this.hibernateTemplate.update(d);
	}
	
	@Transactional
	public void updateFees(Doctor d, String fees) {
		d.setFees(fees);
		this.hibernateTemplate.update(d);
	}
	@Transactional
	public void updatepass(Admin d) {
		d.setPassword(d.getPassword());
		d.setUserName(d.getUserName());
		this.hibernateTemplate.update(d);
	}
	
	
	
	
	
	
}
