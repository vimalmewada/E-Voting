package com.evoting.dto;

import java.sql.Date;

public class VoteDto {
	private int voting_store_id;
	private int election_id;
	private int candidate_id;
	private int voter_id;
	private Date created_at;
	private Date updated_at;
	public int getVoting_store_id() {
		return voting_store_id;
	}
	public void setVoting_store_id(int voting_store_id) {
		this.voting_store_id = voting_store_id;
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
	public int getVoter_id() {
		return voter_id;
	}
	public void setVoter_id(int voter_id) {
		this.voter_id = voter_id;
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
	@Override
	public String toString() {
		return "VoteDto [voting_store_id=" + voting_store_id + ", election_id=" + election_id + ", candidate_id="
				+ candidate_id + ", voter_id=" + voter_id + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}
	
}
