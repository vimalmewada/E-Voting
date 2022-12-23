package com.evoting.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.evoting.dto.CreateElectionDto;
import com.evoting.dto.VoteDto;
import com.evoting.dto.Voter;
import com.evoting.utility.Constant;
import com.evoting.utility.DatabaseConnection;
import com.evoting.utility.SqlQueries;

public class VoterDao {

	static DatabaseConnection db;
	static Connection connect;

	public int save(Voter voter) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.INSERT_VOTER_QUERY);
			stmt.setString(1, voter.getName());
			stmt.setString(2, voter.getEmail());
			stmt.setDate(3, voter.getDob());
			stmt.setString(4, voter.getAddress());
			stmt.setString(5, voter.getPassword());
			stmt.setString(6, voter.getMobile());
			stmt.setInt(7, voter.getIs_active());
			isInserted = stmt.executeUpdate();
			// System.out.println(isInserted + " records inserted");
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
			return isInserted;

		}

		return isInserted;

	}

	public Voter checkLogin(String userId, String password) {
		Voter voter =null;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.VOTER_LOGIN_CHECK_QUERY);
			stmt.setString(1, userId);
			stmt.setString(2, userId);
			stmt.setString(3, password);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				voter= new Voter();
				voter.setVoter_id(resultSet.getInt(Constant.VOTER_ID));
				voter.setName(resultSet.getString(Constant.NAME));
				voter.setEmail(resultSet.getString(Constant.EMAIL));
				voter.setAddress(resultSet.getString(Constant.ADDRESS));
				voter.setMobile(resultSet.getString(Constant.MOBILE));
				voter.setIs_active(resultSet.getInt(Constant.IS_ACTIVE));
				voter.setCreated_at(resultSet.getDate(Constant.CREATE_AT));
				voter.setUpdate_at(resultSet.getDate(Constant.UPDATE_AT));
				voter.setDob(resultSet.getDate(Constant.DOB));
			}
			// System.out.println(voter);
			return voter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Boolean isDuplicateEmail(String email) {

		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement stmt = connect.prepareStatement(SqlQueries.EMAIL_DUPLICATE_CANDIDATE_CHECK_QUERY);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int countRow = rs.getInt(1);
				if (countRow == 0) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<Voter> voterList() {
		List<Voter> list = new ArrayList<Voter>();

		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.VOTER_LIST_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Voter voter = new Voter();
				voter.setVoter_id(resultSet.getInt("voter_id"));
				voter.setName(resultSet.getString("name"));
				voter.setDob(resultSet.getDate("dob"));
				voter.setAddress(resultSet.getString("address"));
				voter.setEmail(resultSet.getString("email"));
				voter.setMobile(resultSet.getString("mobile"));
				// System.out.println("****"+voter);
				list.add(voter);

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public static Voter getVoterbyId(int id) {
		Voter voter = new Voter();
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.VOTER_QUERY_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				voter.setVoter_id(resultSet.getInt("voter_id"));
				voter.setName(resultSet.getString("name"));
				voter.setEmail(resultSet.getString("email"));
				voter.setDob(resultSet.getDate("dob"));
				voter.setAddress(resultSet.getString("address"));
				voter.setMobile(resultSet.getString("mobile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return voter;
	}

	public int updateVoter(Voter voter) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.UPDATE_VOTER_QUERY);
			preparedStatement.setString(1, voter.getName());
			preparedStatement.setString(2, voter.getEmail());
			preparedStatement.setString(3, voter.getMobile());
			preparedStatement.setString(4, voter.getAddress());
			preparedStatement.setDate(5, voter.getDob());
			preparedStatement.setInt(6, voter.getVoter_id());
			isInserted = preparedStatement.executeUpdate();
			// System.out.println(" update election data "+isInserted);
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isInserted;
	}

	public static Voter approveVoterById(int id) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.APPROVE_VOTER_QUERY);
			preparedStatement.setInt(1, id);
			isInserted = preparedStatement.executeUpdate();
			System.out.println("approveddd"+isInserted);
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public  Voter isActiveOrNot(int voter_id, int is_active) {
		Voter voter = new Voter();
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.ACTIVE_VOTER_QUERY_BY_ID);
			preparedStatement.setInt(1, voter_id);
			preparedStatement.setInt(2, is_active);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				voter.setVoter_id(resultSet.getInt("voter_id"));
				voter.setName(resultSet.getString("name"));
				voter.setEmail(resultSet.getString("email"));
				voter.setDob(resultSet.getDate("dob"));
				voter.setAddress(resultSet.getString("address"));
				voter.setMobile(resultSet.getString("mobile"));
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return voter;
	}

	
}