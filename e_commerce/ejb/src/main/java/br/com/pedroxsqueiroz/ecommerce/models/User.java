package br.com.pedroxsqueiroz.ecommerce.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {
private Integer id;
	
	private String name;
	
	private String email;
	
	private Date birthdate;
	
	private String gender;
	
	private String address;
	
	private String postcode;
	
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String birthdateStr, String email, String password, String address, String postcode, String gender) {
		
		try {
			
			Date birthDate = new SimpleDateFormat("yyyy/MM/dd").parse(birthdateStr);
			this.birthdate = birthDate;
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.postcode = postcode;
		this.address = address;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
