package com.nrifintech.mms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ap_id;
	private int doc_id;
	private int p_id;
	private String doctorName;
	private String patientName;
	private String date;
	private String slot;
	private String status;
	private String specialisation;
	private String fees;
	private String cancel;
	private int review;
	
	public Appointment(int ap_id, int doc_id, int p_id, String doctorName, String patientName, String date, String slot,
			String status, String specialisation, String fees, String cancel, int review) {
		super();
		this.ap_id = ap_id;
		this.doc_id = doc_id;
		this.p_id = p_id;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.date = date;
		this.slot = slot;
		this.status = status;
		this.specialisation = specialisation;
		this.fees = fees;
		this.cancel = cancel;
		this.review=review;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAp_id() {
		return ap_id;
	}

	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Appointment [ap_id=" + ap_id + ", doc_id=" + doc_id + ", p_id=" + p_id + ", doctorName=" + doctorName
				+ ", patientName=" + patientName + ", date=" + date + ", slot=" + slot + ", status=" + status
				+ ", specialisation=" + specialisation + ", fees=" + fees + ", cancel=" + cancel + ",review=" + review + "]";
	}

	
}
