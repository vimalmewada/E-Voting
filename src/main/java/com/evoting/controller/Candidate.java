package com.evoting.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evoting.dao.ElectionDao;
import com.evoting.dto.CandidateDto;
import com.evoting.dto.Voter;
import com.evoting.services.CandidateService;
import com.evoting.services.ElectionService;
import com.evoting.utility.Constant;
import com.evoting.utility.DateFormater;

/**
 * Servlet implementation class Candidate
 */
@WebServlet("/Candidate")
public class Candidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CandidateService candidateService = new CandidateService();
	ElectionService electionService=new ElectionService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter(Constant.METHOD);
		System.out.println("method *******" + method);
		switch (method) {
		case Constant.REGISTER_CANDIDATE:{
			registerCandidate(request,response);
			break;
		}
		case Constant.SAVE: {
			doSave(request,response);
			break;
		}
		case Constant.VIEW_CANDIDATE:{
			getCandidateList(request,response);
			break;
		}
		case Constant.GET_CANDIDATE_BY_ID:{
			getCandidateById(request,response);
			break;
		}
		case Constant.UPDATE_CANDIDATE_BY_ID:{
			updateCandidateById(request,response);
			break;
		}
		case Constant.ACTIVE_CANDIDATE_BY_ID:{
			activeCandidateById(request,response);
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}
	}

	private void activeCandidateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		CandidateDto candidateDto= candidateService.activeCandidateById(id);
		getCandidateList(request,response);
		
	}

	private void updateCandidateById(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("candidate_id"));
		CandidateDto candidateDto=new CandidateDto();
		//System.out.println("11111111"+id);
		candidateDto.setCandidate_id(id);
		
		candidateDto.setName(request.getParameter(Constant.NAME));
		candidateDto.setEmail(request.getParameter(Constant.EMAIL));
		candidateDto.setDob(DateFormater.stringToDate(request.getParameter(Constant.DOB)));
		candidateDto.setMobile(request.getParameter(Constant.MOBILE));
		candidateDto.setAddress(request.getParameter(Constant.ADDRESS));
		
		//System.out.println("ddata inssterd**"+voter);
		candidateService.updateCandidate(candidateDto);
		getCandidateList(request, response);
		
	}

	private void getCandidateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		//	System.out.println(""+id);
			CandidateDto candidateDto  = candidateService.getCandidateById(id);
			request.setAttribute("candidatedto", candidateDto);
			request.setAttribute("electionList", electionService.getElecctionList());
			RequestDispatcher dispatcher=request.getRequestDispatcher("updateCandidate.jsp");
			dispatcher.forward(request, response);
		
	}

	private void getCandidateList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setAttribute("candidateList", candidateService.getCandidateList());
		RequestDispatcher dispatcher = request.getRequestDispatcher("view_candidate.jsp");
		dispatcher.forward(request, response);
	
		
	}

	protected void registerCandidate(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		
		System.out.println(electionService.getElecctionList());
		//	dao.createElecction();
		request.setAttribute("electionList", electionService.getElecctionList());
		RequestDispatcher dispatcher=request.getRequestDispatcher("register_candidate.jsp");
		dispatcher.forward(request, response);
		

	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setElection_id(Integer.parseInt(request.getParameter(Constant.ELECTION_ID)));
		candidateDto.setName(request.getParameter(Constant.NAME));
		candidateDto.setEmail(request.getParameter(Constant.EMAIL));
		candidateDto.setMobile(request.getParameter(Constant.MOBILE));	
		candidateDto.setAddress(request.getParameter(request.getParameter(Constant.ADDRESS)));
		candidateDto.setDob(DateFormater.stringToDate(request.getParameter(Constant.DOB)));
		candidateDto.setPassword(request.getParameter(Constant.PASSWORD));

		if (candidateService.isDuplicateEmail(candidateDto.getEmail())) {
			request.setAttribute("error", "Not a Valid Email Address !! Please try again");
			request.setAttribute("candidate", candidateDto);
			request.setAttribute("electionList", electionService.getElecctionList());
			RequestDispatcher req = request.getRequestDispatcher("register_candidate.jsp");
			req.forward(request, response);
		} else {
			candidateService.saveCandidate(candidateDto);
			request.setAttribute("sucess", "Sucessfully registred and waiting for the approval");
			RequestDispatcher req = request.getRequestDispatcher("register_candidate.jsp");
			req.forward(request, response);
			// System.out.println(" not duplicate!!" +voter);
		}
	}
}
