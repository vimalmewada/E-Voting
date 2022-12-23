package com.evoting.services;

import java.util.List;

import com.evoting.dao.CandidateDao;
import com.evoting.dto.CandidateDto;

public class CandidateService {
	CandidateDao candidateDao = new CandidateDao();

	public int saveCandidate(CandidateDto candidateDto) {
		return candidateDao.saveCandidate(candidateDto);
	}
	public boolean isDuplicateEmail(String email) {
		return candidateDao.isDuplicateEmail(email);
	}
	public CandidateDto loginCandidate(String userId, String password) {
		return candidateDao.checkCandidateLogin(userId,password);
	}
	public List<CandidateDto> getCandidateByElectionId(int election_id) {
	 return candidateDao.getCandidateByElectionId(election_id);
		
	}
	public  List<CandidateDto>  getCandidateList() {
		return candidateDao.getCandidateList();
	}
	public CandidateDto getCandidateById(int id) {
		
		return CandidateDao.getCandidateById(id);
	}
	public int updateCandidate(CandidateDto candidateDto) {
		return candidateDao.updateCandidate(candidateDto);
		
	}
	public CandidateDto activeCandidateById(int id) {
		// TODO Auto-generated method stub
		return candidateDao.activeCandidateById(id);
	}

}
