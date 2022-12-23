package com.evoting.dto;

public class resultDto {
	 private String name;
	 private String election_name;
	 private int vote_count;
	 private int election_id ;
	 private int candidate_id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElection_name() {
		return election_name;
	}
	public void setElection_name(String election_name) {
		this.election_name = election_name;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	public int getElection_id() {
		return election_id;
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
	@Override
	public String toString() {
		return "resultDto [name=" + name + ", election_name=" + election_name + ", vote_count=" + vote_count
				+ ", election_id=" + election_id + ", candidate_id=" + candidate_id + "]";
	}
	 
	 
}
