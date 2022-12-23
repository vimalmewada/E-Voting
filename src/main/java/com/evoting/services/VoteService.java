package com.evoting.services;

import com.evoting.dao.VoteDao;
import com.evoting.dao.VoterDao;
import com.evoting.dto.VoteDto;
import com.evoting.dto.Voter;

public class VoteService {
	VoteDao dao = new VoteDao();

	public int castVote(VoteDto dto) {
		return dao.castVote(dto);
	}

	public VoteDto isVoteDuplicate(int election_id, int voter_id) {
	
		return dao.isVoteDuplicate(election_id,voter_id);
	}

	

}
