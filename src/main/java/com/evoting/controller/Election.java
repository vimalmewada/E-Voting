package com.evoting.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.evoting.dto.CandidateDto;
import com.evoting.dto.CreateElectionDto;
import com.evoting.dto.resultDto;
import com.evoting.services.CandidateService;
import com.evoting.services.ElectionService;
import com.evoting.services.VoteService;
import com.evoting.utility.Constant;
import com.evoting.utility.DateFormater;

/**
 * Servlet implementation class CreateElection
 */
@WebServlet("/Election")
public class Election extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ElectionService createElectionService=new ElectionService();
	CandidateService candidateService=new CandidateService();
	VoteService service=new VoteService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method=request.getParameter(Constant.METHOD);
		System.out.println("method *******"+method);
		switch (method) {
		case Constant.SAVE: {
			createElection(request, response);
			break;
		}
		case Constant.ELECTION_LIST:{
			getElectionList(request, response);
			break;
		}
		case Constant.GET_ELECTION_BY_ID:{
			getElectionById(request,response);
			break;
		}
		case Constant.DELETE_ELECTION_BY_ID:{
			deleteElectionById(request,response);
			break;
		}
		case Constant.UPDATE_ELECTION:{
			updateElection(request,response);
			break;
		}
		case Constant.GET_ELECTION_DETAILS:{
			getElectionDetails(request,response);
			break;
		}
		case Constant.DECLARE_ELECTION_BY_ID:{
			declareElectionById(request,response);
			break;
		}
		case Constant.RESULT:{
			showResult(request,response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}
	}
	
	
	private void showResult(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		DateFormater dateFormater=new DateFormater();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		System.out.println(date);
		//LocalDate date=LocalDate.now();
		
		Map<Integer, List<resultDto>> dtos= createElectionService.electionResultOut(date);
		
		//System.out.println(dtos);
		
		
		if(dtos.size()!=0) {	
		request.setAttribute("result", dtos);
		RequestDispatcher dispatcher=request.getRequestDispatcher("result.jsp");
		dispatcher.forward(request, response);
		
		}
		else{
			request.setAttribute("message", "No Result Declare");
			RequestDispatcher dispatcher=request.getRequestDispatcher("result.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void declareElectionById(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		
		 createElectionService.declareElectionById(id);
		getElectionList(request, response);
		
	}

	private void getElectionDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		int election_id=Integer.parseInt(request.getParameter("election_id"));
		int voter_id=Integer.parseInt(request.getParameter("voter_id"));
 		CreateElectionDto createElectionDto = createElectionService.getElectionById(election_id);
		request.setAttribute("electiondto", createElectionDto);
     	request.setAttribute("candidatedto",  candidateService.getCandidateByElectionId(election_id));
     	if(service.isVoteDuplicate(election_id, voter_id) != null) {
     		
     		RequestDispatcher dispatcher=request.getRequestDispatcher("voting.jsp");
    		dispatcher.forward(request, response);     	
     		System.out.println("duplicate!!!S");
     	}else {
     		
     		RequestDispatcher dispatcher=request.getRequestDispatcher("vote.jsp");
    		dispatcher.forward(request, response);     
    		
     	}
		  
	}


	private void updateElection(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		int id=Integer.parseInt(request.getParameter("electionId"));
		CreateElectionDto createElectionDto=new CreateElectionDto();
		//System.out.println("11111111"+id);
		createElectionDto.setElection_id(id);

		createElectionDto.setElection_name(request.getParameter(Constant.ELECTION_NAME));
		createElectionDto.setNomination_start_date(DateFormater.stringToDate(request.getParameter(Constant.NOMINATION_START_DATE)));
		createElectionDto.setNomination_end_date(DateFormater.stringToDate(request.getParameter(Constant.NOMINATION_END_DATE)));
		createElectionDto.setElection_date(DateFormater.stringToDate(request.getParameter(Constant.ELECTION_DATE)));
		createElectionDto.setResult_date(DateFormater.stringToDate(request.getParameter(Constant.ELECTION_RESULT_DATE)));
		createElectionDto.setDescription(request.getParameter(Constant.DESCRIPTION));
		
		//System.out.println("ddata inssterd"+createElectionDto);
		
		createElectionService.updateElection(createElectionDto);
		getElectionList(request, response);
		
	}

	private void deleteElectionById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		//System.out.println(""+id);
		//System.out.println("hii");
		int createElectionDto = createElectionService.deleteElectionById(id);
		getElectionList(request, response);
		}
		


	private void getElectionById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
	//	System.out.println(""+id);
		CreateElectionDto createElectionDto = createElectionService.getElectionById(id);
		request.setAttribute("electiondto", createElectionDto);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("updateElection.jsp");
		dispatcher.forward(request, response);
		}
	
	
	protected void createElection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateElectionDto createElectionDto=new CreateElectionDto();
		createElectionDto.setElection_name(request.getParameter(Constant.ELECTION_NAME));
		createElectionDto.setNomination_start_date(DateFormater.stringToDate(request.getParameter(Constant.NOMINATION_START_DATE)));
		createElectionDto.setNomination_end_date(DateFormater.stringToDate(request.getParameter(Constant.NOMINATION_END_DATE)));
		createElectionDto.setElection_date(DateFormater.stringToDate(request.getParameter(Constant.ELECTION_DATE)));
		createElectionDto.setResult_date(DateFormater.stringToDate(request.getParameter(Constant.ELECTION_RESULT_DATE)));
		createElectionDto.setDescription(request.getParameter(Constant.DESCRIPTION));
		//System.out.println("ddata inssterd"+createElectionDto);
		createElectionService.saveELection(createElectionDto);
		getElectionList(request, response);

	}

	protected void getElectionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("electionList", createElectionService.getElecctionList());
			RequestDispatcher dispatcher=request.getRequestDispatcher("viewElection.jsp");
			dispatcher.forward(request, response);
	}
	
	
	}


