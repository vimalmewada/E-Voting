package com.evoting.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.evoting.dao.ElectionDao;
import com.evoting.dto.CreateElectionDto;
import com.evoting.dto.resultDto;

public class ElectionService {
	ElectionDao electionDao = new ElectionDao();

	public int saveELection(CreateElectionDto dto) {
		return electionDao.saveElection(dto);
	}

	public List<CreateElectionDto> getElecctionList() {
		return electionDao.createElecction();

	}

	public CreateElectionDto getElectionById(int id) {

		return electionDao.getELectionById(id);
	}

	public int deleteElectionById(int id) {

		return electionDao.deleteElectionById(id);
	}

	public int updateElection(CreateElectionDto createElectionDto) {
		return electionDao.updateElection(createElectionDto);

	}

	public int declareElectionById(int id) {
		return electionDao.declareElectionById(id);

	}

	public Map<Integer, List<resultDto>> electionResultOut(String date) {
		
		List<resultDto> list1 = electionDao.electionResultOut(date);
	//	Map<String, String> list = new HashMap<>();
		
		Map<Integer, List<resultDto>> electionresultlistGrouped =  list1.stream().collect(Collectors.groupingBy(w -> w.getElection_id()));
		System.out.println(electionresultlistGrouped);
		
		return electionresultlistGrouped;
	}

}
/*
 * 
 * int id=1; for(resultDto dto1:list1) { if(id==dto.getElection_id()) {
 * 
 * list.put(dto1.getCandidate_id(),dto1.getName()); }} }
 * System.out.println(list);
 * 
 */
