package com.evoting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.evoting.dto.AdminDto;
import com.evoting.dto.CandidateDto;
import com.evoting.dto.Voter;
import com.evoting.services.AdminService;
import com.evoting.services.CandidateService;
import com.evoting.services.LoginService;
import com.evoting.utility.Constant;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String method = request.getParameter(Constant.METHOD);

		switch (method) {
		case Constant.VOTER_LOGIN: {
			doLogin(request, response);
			break;
		}
		case Constant.ADMIN_LOGIN: {
			doAdminLogin(request, response);
			break;
		}
		case Constant.CANDIDATE_LOGIN: {
			doCandidateLogin(request, response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + method);
		}

	}

	private void doCandidateLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		// System.out.println(userId+" "+password);
		CandidateService candidateService = new CandidateService();
		CandidateDto candidateDto = candidateService.loginCandidate(userId, password);
		// System.out.println("**"+candidateDto);
		if (candidateDto != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser", candidateDto);
			response.sendRedirect("candidate_portal.jsp"); // logged-in page
		} else {
			request.setAttribute("title", "Candidate Login");
			request.setAttribute("method", "candidateLogin");
			request.setAttribute("error", "Not a Valid Login Details !! Please try again");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		LoginService loginService = new LoginService();
		Voter voter = loginService.loginVoter(userId, password);
		// System.out.println(voter);
		if (voter != null) {

			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser", voter);
			response.sendRedirect("evoting.jsp"); // logged-in page
		} else {
			request.setAttribute("title", "Voter Login");
			request.setAttribute("method", "voterLogin");
			request.setAttribute("error", "Not a Valid Login Details !! Please try again");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

	protected void doAdminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		AdminService adminService = new AdminService();
		AdminDto adminDto = adminService.adminLogin(userId, password);
		if (adminDto != null) {

			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser", adminDto);
			response.sendRedirect("admin_portal.jsp"); // logged-in page
		} else {
			request.setAttribute("title", "Admin Login");
			request.setAttribute("method", "adminLogin");
			request.setAttribute("error", "Not a Valid Login Details !! Please try again");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
}
