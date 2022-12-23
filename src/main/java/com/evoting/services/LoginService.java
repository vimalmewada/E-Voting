package com.evoting.services;

import com.evoting.dao.VoterDao;
import com.evoting.dto.Voter;

public class LoginService {

	VoterDao voterDao  = new VoterDao();
	public Voter loginVoter(String userId, String password) {
		return voterDao.checkLogin(userId, password);
		 
	}
}
