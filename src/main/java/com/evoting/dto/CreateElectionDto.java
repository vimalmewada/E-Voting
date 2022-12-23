package com.evoting.dto;

import java.sql.Date;

public class CreateElectionDto {
		private int election_id;
		private String election_name;
		private Date nomination_start_date;
		private Date nomination_end_date;
		private Date election_date;
		private Date result_date;
		private String description;
		private int is_result;
		public int getIs_result() {
			return is_result;
		}
		public void setIs_result(int is_result) {
			this.is_result = is_result;
		}
		public int getElection_id() {
			return election_id;
		}
		public void setElection_id(int election_id) {
			this.election_id = election_id;
		}
		public String getElection_name() {
			return election_name;
		}
		public void setElection_name(String election_name) {
			this.election_name = election_name;
		}
		public Date getNomination_start_date() {
			return nomination_start_date;
		}
		public void setNomination_start_date(Date nomination_start_date) {
			this.nomination_start_date = nomination_start_date;
		}
		public Date getNomination_end_date() {
			return nomination_end_date;
		}
		public void setNomination_end_date(Date nomination_end_date) {
			this.nomination_end_date = nomination_end_date;
		}
		public Date getElection_date() {
			return election_date;
		}
		public void setElection_date(Date election_date) {
			this.election_date = election_date;
		}
		public Date getResult_date() {
			return result_date;
		}
		public void setResult_date(Date result_date) {
			this.result_date = result_date;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public String toString() {
			return "CreateElection [election_id=" + election_id + ", election_name=" + election_name
					+ ", nomination_start_date=" + nomination_start_date + ", nomination_end_date="
					+ nomination_end_date + ", election_date=" + election_date + ", result_date=" + result_date
					+ ", description=" + description + "]";
		}
		
} 
