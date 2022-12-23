package com.evoting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evoting.dto.CreateElectionDto;
import com.evoting.dto.VoteDto;
import com.evoting.dto.Voter;
import com.evoting.services.ElectionService;
import com.evoting.services.VoteService;
import com.evoting.utility.Constant;

@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VoteDto dto = new VoteDto();
	VoteService voterService = new VoteService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hiiiii");
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter(Constant.METHOD);
		System.out.println("method -----" + method);

		switch (method) {
		case Constant.SHOW_ELECTION: {
			showElection(request, response);
			break;
		}
		case Constant.VOTE: {
			doVote(request, response);
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}

	}

	private void doVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int election_id = Integer.parseInt(request.getParameter(Constant.ELECTION_ID));
		int voter_id = Integer.parseInt(request.getParameter(Constant.VOTER_ID));
		int candidate_id = Integer.parseInt(request.getParameter(Constant.CANDIDATE_ID));
			dto.setElection_id(election_id);
			dto.setVoter_id(voter_id);
			dto.setCandidate_id(candidate_id);
			voterService.castVote(dto);	
			 showElection(request, response);
			
	}
	
	protected void showElection(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Voter voter = (Voter) request.getSession(true).getAttribute("currentSessionUser");
		 Map<Integer, Boolean> duplicateCheckMap= new HashMap<Integer, Boolean>();
		ElectionService electionService = new ElectionService();
		List<CreateElectionDto> list=electionService.getElecctionList();
		for(CreateElectionDto createElectionDto:list) {
			VoteDto voteDto= voterService.isVoteDuplicate(createElectionDto.getElection_id(), voter.getVoter_id());
			duplicateCheckMap.put(createElectionDto.getElection_id(),voteDto!=null);
			
		}
		request.setAttribute("duplicateCheckMap",duplicateCheckMap);
		request.setAttribute("electionList", electionService.getElecctionList());
		RequestDispatcher dispatcher = request.getRequestDispatcher("voting.jsp");
		dispatcher.forward(request, response);
		
	}
	
}
