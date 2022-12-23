package com.evoting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.evoting.dto.CandidateDto;
import com.evoting.dto.Voter;
import com.evoting.utility.DatabaseConnection;
import com.evoting.utility.SqlQueries;


public class CandidateDao {
	static DatabaseConnection db;
	static Connection connect;

	public int saveCandidate(CandidateDto candidateDto) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.INSERT_CANDIDATE_QUERY);
			stmt.setInt(1, candidateDto.getElection_id());
			stmt.setString(2, candidateDto.getName());
			stmt.setString(3, candidateDto.getEmail());
			stmt.setString(4, candidateDto.getAddress());
			stmt.setString(5, candidateDto.getMobile());
			stmt.setDate(6, candidateDto.getDob());
			stmt.setString(7, candidateDto.getPassword());
			isInserted = stmt.executeUpdate();
			System.out.println(isInserted + " records inserted*****");
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
			return isInserted;
		}
		return isInserted;
	}

	public boolean isDuplicateEmail(String email) {
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.EMAIL_DUPLICATE_CANDIDATE_CHECK_QUERY);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int countRow = rs.getInt("count");
				if (countRow == 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public CandidateDto checkCandidateLogin(String userId, String password) {
		CandidateDto candidateDto=null;
	try {
		db = DatabaseConnection.getInstance();
		connect = db.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.CANDIDATE_LOGIN_CHECK_QUERY);
		preparedStatement.setString(1, userId);
		preparedStatement.setString(2, userId);
		preparedStatement.setString(3, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			candidateDto = new CandidateDto();
			candidateDto.setCandidate_id(resultSet.getInt("candidate_id"));
			candidateDto.setName(resultSet.getString("name"));
			candidateDto.setEmail(resultSet.getString("email"));
			candidateDto.setMobile(resultSet.getString("mobile"));
			candidateDto.setPassword(resultSet.getString("password"));
			candidateDto.setElection_id(resultSet.getInt("election_id"));
			candidateDto.setDob(resultSet.getDate("dob"));
			candidateDto.setAddress(resultSet.getString("address"));
			candidateDto.setCreated_at(resultSet.getDate("created_at"));
			candidateDto.setUpdated_at(resultSet.getDate("updated_at"));
		}
		//System.out.println(candidateDto);
		return candidateDto;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}

	public List<CandidateDto> getCandidateByElectionId(int election_id) {
		CandidateDto candidateDto=null;
			List <CandidateDto> list=new ArrayList<>();
			try {
				db = DatabaseConnection.getInstance();
				connect = db.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.CANDIDATE_BY_ELECTION_ID_QUERY);
				preparedStatement.setInt(1, election_id );
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					 candidateDto = new CandidateDto();
					 candidateDto.setCandidate_id(resultSet.getInt("candidate_id"));
						candidateDto.setName(resultSet.getString("name"));
						candidateDto.setEmail(resultSet.getString("email"));
						candidateDto.setMobile(resultSet.getString("mobile"));
						candidateDto.setPassword(resultSet.getString("password"));
						candidateDto.setElection_id(resultSet.getInt("election_id"));
						candidateDto.setDob(resultSet.getDate("dob"));
						candidateDto.setAddress(resultSet.getString("address"));
						candidateDto.setCreated_at(resultSet.getDate("created_at"));
						candidateDto.setUpdated_at(resultSet.getDate("updated_at"));
						list.add(candidateDto);
				}
			// slelect * from candidate where election_id=election_id
		return list;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
}
	public List<CandidateDto> getCandidateList() {
		CandidateDto candidateDto=null;
			List <CandidateDto> list=new ArrayList<>();
			try {
				db = DatabaseConnection.getInstance();
				connect = db.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.CANDIDATE_LIST_QUERY);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					 candidateDto = new CandidateDto();
					 	candidateDto.setElection_id(resultSet.getInt("election_id"));
					 	candidateDto.setCandidate_id(resultSet.getInt("candidate_id"));
						candidateDto.setName(resultSet.getString("name"));
						candidateDto.setEmail(resultSet.getString("email"));
						candidateDto.setMobile(resultSet.getString("mobile"));
						candidateDto.setPassword(resultSet.getString("password"));
						candidateDto.setElection_id(resultSet.getInt("election_id"));
						candidateDto.setElection_id(resultSet.getInt("is_active"));
						
						candidateDto.setDob(resultSet.getDate("dob"));
						candidateDto.setAddress(resultSet.getString("address"));
						candidateDto.setCreated_at(resultSet.getDate("created_at"));
						candidateDto.setUpdated_at(resultSet.getDate("updated_at"));
						list.add(candidateDto);
				}
			// slelect * from candidate where election_id=election_id
		return list;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
}

	public static CandidateDto getCandidateById(int id) {
		CandidateDto candidateDto=new CandidateDto();
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.CANDIDATE_QUERY_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				candidateDto.setElection_id(resultSet.getInt("election_id"));
				candidateDto.setCandidate_id(resultSet.getInt("candidate_id"));
				candidateDto.setName(resultSet.getString("name"));
				candidateDto.setEmail(resultSet.getString("email"));
				candidateDto.setDob(resultSet.getDate("dob"));
				candidateDto.setAddress(resultSet.getString("address"));
				candidateDto.setMobile(resultSet.getString("mobile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateDto;
	}

	public int updateCandidate(CandidateDto candidateDto) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.UPDATE_CANDIDATE_QUERY);
			preparedStatement.setString(1, candidateDto.getName());
			preparedStatement.setString(2, candidateDto.getEmail());
			preparedStatement.setString(3, candidateDto.getMobile());
			preparedStatement.setString(4, candidateDto.getAddress());
			preparedStatement.setDate(5, candidateDto.getDob());
			preparedStatement.setInt(6, candidateDto.getCandidate_id());
			isInserted = preparedStatement.executeUpdate();
			// System.out.println(" update election data "+isInserted);
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isInserted;
	}

	public CandidateDto activeCandidateById(int id) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.ACTIVE_CANDIDATE_BY_QUERY);
			preparedStatement.setInt(1, id);
			isInserted = preparedStatement.executeUpdate();
			System.out.println("approveddd candidate"+isInserted);
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}