package com.evoting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evoting.utility.Constant;

/**
 * Servlet implementation class ReDirectLogin
 */
@WebServlet("/ReDirectLogin")
public class ReDirectLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String method = request.getParameter(Constant.METHOD);
		switch (method) {
		case Constant.VOTER_LOGIN: {
			request.setAttribute("title","Voter Login");
			request.setAttribute("method","voterLogin");
			break;
		}
		case Constant.CANDIDATE_LOGIN: {
			request.setAttribute("title","Candidate Login");
			request.setAttribute("method","candidateLogin");
			break;
		}
		case Constant.ADMIN_LOGIN: {
			request.setAttribute("title","Admin Login");
			request.setAttribute("method","adminLogin");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
	}
}
