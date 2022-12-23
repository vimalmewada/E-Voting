
package com.evoting.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.evoting.services.VoterService;
import com.evoting.utility.Constant;
import com.evoting.utility.DateFormater;

/**
 * Servlet implementation class voterregistration
 */
@WebServlet("/Voterregistration")
public class Voterregistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VoterService voterService = new VoterService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter(Constant.METHOD);
		System.out.println("method -----" + method);
		switch (method) {
		case Constant.SAVE: {
			doSave(request, response);
			break;
		}
		case Constant.VOTER_LIST: {
			getVoterList(request, response);
			break;
		}
		case Constant.GET_VOTER_BY_ID: {
			getVoterById(request, response);
			break;
		}
		case Constant.UPDATE_VOTER_BY_ID: {
			updateVoter(request, response);
			break;
		}
		case Constant.APPROVE_VOTER_BY_ID: {
			approveVoterById(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}

	}

	private void approveVoterById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		// System.out.println(id);
		Voter voter = VoterService.approveVoterById(id);
		getVoterList(request, response);
	}

	private void updateVoter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("voter_id"));
		Voter voter = new Voter();
		// System.out.println("11111111"+id);
		voter.setVoter_id(id);

		voter.setName(request.getParameter(Constant.NAME));
		voter.setEmail(request.getParameter(Constant.EMAIL));
		voter.setDob(DateFormater.stringToDate(request.getParameter(Constant.DOB)));
		voter.setMobile(request.getParameter(Constant.MOBILE));
		voter.setAddress(request.getParameter(Constant.ADDRESS));

		// System.out.println("ddata inssterd**"+voter);
		voterService.updateVoter(voter);
		getVoterList(request, response);

	}

	private void getVoterById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		// System.out.println(""+id);
		Voter voter = VoterService.getVoterById(id);
		request.setAttribute("voterdto", voter);

		RequestDispatcher dispatcher = request.getRequestDispatcher("updateVoter.jsp");
		dispatcher.forward(request, response);
	}

	protected void getVoterList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("hello11");
		request.setAttribute("VoterList", voterService.getVoterList());
		Map<Integer, Boolean> isActiveOrNot = new HashMap<Integer, Boolean>();
		List<Voter> list = voterService.getVoterList();
		for (Voter voter : list) {
		//	Voter voterDto = voterService.isActiveOrNot(voter.getVoter_id(), voter.getIs_active());
		Boolean isActive=null;
		if(voter.getIs_active()==1) {
			isActive=true;
		}
		else {
			isActive=false;
		}
		isActiveOrNot.put(voter.getVoter_id(), isActive );
		}
		request.setAttribute("isActiveOrNot", isActiveOrNot);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view_voters.jsp");
		dispatcher.forward(request, response);

	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Voter voter = new Voter();

		voter.setName(request.getParameter(Constant.NAME));
		voter.setEmail(request.getParameter(Constant.EMAIL));
		voter.setMobile(request.getParameter(Constant.MOBILE));
		voter.setAddress(request.getParameter(Constant.ADDRESS));
		voter.setDob(DateFormater.stringToDate(request.getParameter(Constant.DOB)));
		voter.setPassword(request.getParameter(Constant.PASSWORD));

		if (voterService.isDuplicateEmail(voter.getEmail())) {
			request.setAttribute("error", "Not a Valid Email Address !! Please try again");
			request.setAttribute("voter", voter);
			RequestDispatcher req = request.getRequestDispatcher("register.jsp");

			// System.out.println(" duplicate" +voter);
			req.forward(request, response);
		} else {
			voterService.saveVoter(voter);
			request.setAttribute("sucess", "Sucessfully registred and waiting for the approval");
			RequestDispatcher req = request.getRequestDispatcher("register.jsp");
			req.forward(request, response);
			// System.out.println(" not duplicate!!" +voter);
		}

		/*
		 * if( voter.getName().isEmpty() || voter.getEmail().isEmpty() ||
		 * voter.getMobile().isEmpty() || voter.getMobile().length()>10
		 * ||voter.getMobile().length()<10|| voter.getAddress().isEmpty() ||
		 * voter.getPassword().isEmpty()) { response.sendRedirect("register.jsp"); }
		 * else {
		 * 
		 * //req.forward(request, response); }
		 */

		// System.out.println(" xyzzz!" +voter);

		// response.sendRedirect("login.jsp");

	}

}
