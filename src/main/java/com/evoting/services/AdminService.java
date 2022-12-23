package com.evoting.services;

import com.evoting.dao.AdminDao;
import com.evoting.dto.AdminDto;

public class AdminService {
	AdminDao adminDao=new AdminDao();
	public AdminDto adminLogin(String userId, String password) {
		return adminDao.checkAdminLogin(userId,password);
		 
	}
}
