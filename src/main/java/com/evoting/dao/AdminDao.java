package com.evoting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.evoting.dto.AdminDto;

import com.evoting.utility.DatabaseConnection;
import com.evoting.utility.SqlQueries;

public class AdminDao {
	DatabaseConnection db;
	Connection connect;

	public AdminDto checkAdminLogin(String userId, String password) {
		AdminDto adminDto =null;
		
		try {
			db = DatabaseConnection.getInstance();
			connect = db.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement(SqlQueries.ADMIN_LOGIN_CHECK_QUERY);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, password);
			ResultSet resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				adminDto= new AdminDto();
				adminDto.setAdmin_id(resultSet.getInt("admin_id"));
				adminDto.setName(resultSet.getString("name"));
				adminDto.setEmail(resultSet.getString("email"));
				adminDto.setMobile(resultSet.getString("mobile"));
				adminDto.setPassword(resultSet.getString("password"));
			}
			return adminDto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
