package com.evoting.dto;

public class AdminDto {
   private int admin_id;
   private String name;
   private String email;
   private String mobile;
   private String password;
public int getAdmin_id() {
	return admin_id;
}
public void setAdmin_id(int admin_id) {
	this.admin_id = admin_id;
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
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "AdminDto [admin_id=" + admin_id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
			+ ", password=" + password + "]";
}
   
}
