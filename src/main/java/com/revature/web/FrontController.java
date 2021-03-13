package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.util.RequestHelper;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(FrontController.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/ERS/", "");

		switch (URI) {
		case "login":
			log.info("User logged in");
			RequestHelper.processLogin(request, response);
			break;
			
		case "logout":
			log.info("User logged out");
			RequestHelper.processLogout(request, response);
			break;
			
		case "reimbursements":
			RequestHelper.processReimbursements(request, response);
			break;
			
		case "reimbOfCurrent":
			RequestHelper.processReimbOfCurrent(request, response);
			break;
			
		case "error":
			RequestHelper.processError(request, response);
			break;
			
		case "updateInfo":
			RequestHelper.processUpdateInfo(request, response);
			break;
			
		case "allReimburses":
			RequestHelper.processAllReimburseso(request, response);
			break;
			
		case "decision":
			RequestHelper.processDecision(request, response);
			break;
			
		case "employees":
			RequestHelper.processEmployees(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
