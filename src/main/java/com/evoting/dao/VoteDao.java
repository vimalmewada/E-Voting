package com.evoting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import com.evoting.controller.Vote;
import com.evoting.dto.AdminDto;
import com.evoting.dto.VoteDto;
import com.evoting.dto.Voter;
import com.evoting.utility.DatabaseConnection;
import com.evoting.utility.SqlQueries;

public class VoteDao {
	DatabaseConnection db;
	Connection connect;

	public int castVote(VoteDto dto) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.INSERT_VOTE_QUERY);
			stmt.setInt(1, dto.getElection_id());
			stmt.setInt(2, dto.getCandidate_id());
			stmt.setInt(3, dto.getVoter_id());

			isInserted = stmt.executeUpdate();
			System.out.println(isInserted + "vote finally submit");
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
			return isInserted;
		}
		return isInserted;
	}

	public VoteDto isVoteDuplicate(int election_id, int voter_id) {
		VoteDto dto=null; 
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.DUPLICATE_VOTE_QUERY);
			preparedStatement.setInt(1, election_id);
			preparedStatement.setInt(2,voter_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dto=new VoteDto();
				dto.setElection_id(election_id);
				
				dto.setVoter_id(voter_id);
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

}
