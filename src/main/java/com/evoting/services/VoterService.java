package com.evoting.services;


import java.util.List;

import com.evoting.dao.VoterDao;
import com.evoting.dto.VoteDto;
import com.evoting.dto.Voter;

public class VoterService {
	VoterDao voterDao=new VoterDao();
	
	public int saveVoter(Voter voter) {
		return voterDao.save(voter);
	}
	
	public Boolean isDuplicateEmail(String email) {
		return voterDao.isDuplicateEmail(email);
	}

	public  List<Voter> getVoterList() {
		return voterDao.voterList();
	}

	public static Voter getVoterById(int id) {
		return VoterDao.getVoterbyId(id);
	}

	public int updateVoter(Voter voter) {
		return voterDao.updateVoter(voter);
		
	}

	public static Voter approveVoterById(int id) {
		
		return VoterDao.approveVoterById(id);
	}

	public Voter isActiveOrNot(int voter_id, int is_active) {
		// TODO Auto-generated method stub
		return voterDao.isActiveOrNot(voter_id, is_active);
	}

}