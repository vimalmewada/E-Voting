package com.evoting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.evoting.dto.CreateElectionDto;
import com.evoting.dto.resultDto;
import com.evoting.utility.DatabaseConnection;
import com.evoting.utility.SqlQueries;

public class ElectionDao {
	DatabaseConnection db;
	Connection connect;

	public int saveElection(CreateElectionDto dto) {
		int isInserted = 0;

		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.INSERT_CREATE_ELECTION_QUERY);
			preparedStatement.setString(1, dto.getElection_name());
			preparedStatement.setDate(2, dto.getNomination_start_date());
			preparedStatement.setDate(3, dto.getNomination_end_date());
			preparedStatement.setDate(4, dto.getElection_date());
			preparedStatement.setDate(5, dto.getResult_date());
			preparedStatement.setString(6, dto.getDescription());
			isInserted = preparedStatement.executeUpdate();
			connect.close();
			//System.out.println("insert create election data ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isInserted;

	}
	public List<CreateElectionDto> createElecction() {
		List<CreateElectionDto> list=new ArrayList<CreateElectionDto>();
	
	
		try {
			db=DatabaseConnection.getInstance();
			connect=db.getConnection();
			PreparedStatement preparedStatement=connect.prepareStatement(SqlQueries.ELECTION_QUERY);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				CreateElectionDto createElectionDto=new CreateElectionDto();
								createElectionDto.setElection_id(resultSet.getInt("election_id"));
								createElectionDto.setElection_name(resultSet.getString("election_name"));
								createElectionDto.setNomination_start_date(resultSet.getDate("nomination_start_date"));
								createElectionDto.setNomination_end_date(resultSet.getDate("nomination_end_date"));
								createElectionDto.setElection_date(resultSet.getDate("election_date"));
								createElectionDto.setResult_date(resultSet.getDate("result_date"));
								createElectionDto.setDescription(resultSet.getString("description"));
								list.add(createElectionDto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}
	public CreateElectionDto getELectionById(int id) {
		CreateElectionDto createElectionDto=new CreateElectionDto();
		try {
			db=DatabaseConnection.getInstance();
			connect=db.getConnection();
			PreparedStatement preparedStatement=connect.prepareStatement(SqlQueries.ELECTION_QUERY_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
								createElectionDto.setElection_id(resultSet.getInt("election_id"));
								createElectionDto.setElection_name(resultSet.getString("election_name"));
								createElectionDto.setNomination_start_date(resultSet.getDate("nomination_start_date"));
								createElectionDto.setNomination_end_date(resultSet.getDate("nomination_end_date"));
								createElectionDto.setElection_date(resultSet.getDate("election_date"));
								createElectionDto.setResult_date(resultSet.getDate("result_date"));
								createElectionDto.setDescription(resultSet.getString("description"));
								}
		} catch (SQLException e){
			
			e.printStackTrace();
		}
		return createElectionDto;
	}
	public int deleteElectionById(int id) {
		int isDeleted=0;
		try {
			db=DatabaseConnection.getInstance();
			connect=db.getConnection();
			PreparedStatement preparedStatement=connect.prepareStatement(SqlQueries.DELETE_ELECTION_BY_ID);
			preparedStatement.setInt(1, id);
			isDeleted = preparedStatement.executeUpdate();
			connect.close();
			//System.out.print(isDeleted);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return isDeleted;
	}
	public int updateElection(CreateElectionDto createElectionDto) {
		int isInserted = 0;
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.UPDATE_ELECTION_QUERY);
			preparedStatement.setString(1, createElectionDto.getElection_name());
			preparedStatement.setDate(2, createElectionDto.getNomination_start_date());
			preparedStatement.setDate(3, createElectionDto.getNomination_end_date());
			preparedStatement.setDate(4, createElectionDto.getElection_date());
			preparedStatement.setDate(5, createElectionDto.getResult_date());
			preparedStatement.setString(6, createElectionDto.getDescription());
			preparedStatement.setInt(7, createElectionDto.getElection_id());
			isInserted = preparedStatement.executeUpdate();
			//System.out.println(" update election data "+isInserted);
			connect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isInserted;
	}
	public int declareElectionById(int id) {
		int isResult=0;
		try {
			db=DatabaseConnection.getInstance();
			connect=db.getConnection();
			PreparedStatement preparedStatement=connect.prepareStatement(SqlQueries.RESULT_DECLARE_BY_ID);
			preparedStatement.setInt(1, id);
			isResult = preparedStatement.executeUpdate();
			connect.close();
			//System.out.print(isDeleted);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isResult;
		
	}
	
	public List<resultDto> electionResultOut(String date) {
		List<resultDto> list=new ArrayList<resultDto>();
		
		try {
			db=DatabaseConnection.getInstance();
			connect=db.getConnection();
			PreparedStatement preparedStatement=connect.prepareStatement(SqlQueries.RESULT_OUT_QUERY);
			preparedStatement.setString(1, date);
			preparedStatement.setBoolean(2, true);		
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				resultDto  resultDto=new resultDto();
								resultDto.setElection_id(resultSet.getInt("election_id"));
								resultDto.setName(resultSet.getString("name"));
								resultDto.setElection_name(resultSet.getString("election_name"));
								resultDto.setVote_count(resultSet.getInt("vote_count"));
								resultDto.setCandidate_id(resultSet.getInt("candidate_id"));
								list.add(resultDto);
			}
		//System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
