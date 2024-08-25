//package com.speriamochemelacavo.turismo2024.users;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "users")
//public class User2 {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	@Column(name = "name")
//	private String name;
//	@Column(name = "surname")
//	private String surname;
//	@Column(name = "username")
//	private String userName;
//	@Column(name = "email")
//	private String email;
//	@Column(name = "phonenumber")
//	private String phoneNumber;
//	@Autowired
//	private Role role;
//
//	public Integer getId() {
//		return id;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//
//	public String getSurname() {
//		return surname;
//	}
//
//	public Role getRole() {
//		return role;
//	}
//
//	public boolean setRole(Role role) {
//		this.role = role;
//		return true;
//	}
//}
//		
//	