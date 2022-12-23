package com.evoting.dto;

import java.sql.Date;

public class CandidateDto {
	private int election_id;
	private int candidate_id;
	private String name;
	private String email;
	private Date dob;
	private String address;
	private String password;
	private String mobile;
	private int is_active;
	private Date created_at;
	private Date updated_at;
	public int getElection_id() {
		return election_id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public void setElection_id(int election_id) {
		this.election_id = election_id;
	}
	public int getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String toString() {
		return "CandidateDto [election_id=" + election_id + ", candidate_id=" + candidate_id + ", name=" + name
				+ ", email=" + email + ", dob=" + dob + ", address=" + address + ", password=" + password + ", mobile="
				+ mobile + ", is_active=" + is_active + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}

	
}
