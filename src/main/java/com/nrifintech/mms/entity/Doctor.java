package com.nrifintech.mms.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doc_id;
	private String name;
	private String email;
	private String status;
	private String specialisation;
	private String fees;
	@Lob
	private byte[] image;
	@Transient
	private String base64Image;
	private String password;
	private String addedBy;
	
	private String phone;
	private int age;
	private String gender;
	private float rating;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Doctor(int doc_id, String name, String email, String status, String specialisation, String fees,
			byte[] image, String base64Image, String password, String addedBy, String phone, int age, String gender,float rating) {
		super();
		this.doc_id = doc_id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.specialisation = specialisation;
		this.fees = fees;
		this.image = image;
		this.base64Image = base64Image;
		this.password = password;
		this.addedBy = addedBy;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.rating = rating;
	}


	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Transient
	public String getBase64Image() {
		return base64Image;
	}

	@Transient
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	

	@Override
	public String toString() {
		return "Doctor [doc_id=" + doc_id + ", name=" + name + ", email=" + email + ", status=" + status
				+ ", specialisation=" + specialisation + ", fees=" + fees + ", image=" + Arrays.toString(image)
				+ ", base64Image=" + base64Image + ", password=" + password + ", addedBy=" + addedBy + ", phone="
				+ phone + ", age=" + age + ", gender=" + gender + ",rating=" + rating + "]";
	}
	
	

	

}
