package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Decision;
import com.revature.models.LoginTemplate;
import com.revature.models.ReimburseTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.UpdateInfoTemplate;
import com.revature.models.Users;
import com.revature.services.UsersService;

public class RequestHelper {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// get data from frontend
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println("接收到的数据是: " + body);

		// convert data into java object
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		// validate employee and manger using EmployeeService
		Users u = UsersService.confirmLogin(loginAttempt.getTypeOfUser(), loginAttempt.getUsername(),
				loginAttempt.getPassword());

		// 第四步：判断用户是否存在
		if (u != null) {
			res.setContentType("application/json");

			// 第五步：给用户创建session
			HttpSession session = req.getSession();
			session.setAttribute("username", loginAttempt.getUsername());

			// 第六步：传回数据给前端
			res.getWriter().println(om.writeValueAsString(u));

		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}

	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 删除session
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}


	public static void processReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 接收用户信息
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println("接收到的数据是: " + body);

		// 转换成对象
		ReimburseTemplate reimburse = om.readValue(body, ReimburseTemplate.class);
		System.out.println("转换成的数据是：" + reimburse);

		// 获取reimbursement
		boolean isInserted = UsersService.insertReimburse(reimburse);

		// 当不是NULL的时候传回数据
		if (isInserted) {
			res.setStatus(200);
		} else {
			res.setStatus(204);
		}
	}

	public static void processReimbOfCurrent(HttpServletRequest req, HttpServletResponse res) throws IOException {

		System.out.println("inside processReimbOfCurren()");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println("接收到的数据是: " + body);

		Users users = om.readValue(body, Users.class);
		List<Reimbursement> list = UsersService.getReimburseById(users.getUser_id());

		System.out.println(list);
		res.getWriter().println(om.writeValueAsString(list));
	}

	public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		try {
//			req.getRequestDispatcher("error.html").forward(req, res);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static void processUpdateInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 获取用户信息
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println("processUpdateInfo-> body" + body);
		// 转换成JAVA对象
		UpdateInfoTemplate updateInfoTemplate = om.readValue(body, UpdateInfoTemplate.class);

		boolean updated = false;
		if (updateInfoTemplate.getType().equals("password")) {
			updated = UsersService.updatePasswordById(updateInfoTemplate);
		} else if (updateInfoTemplate.getType().equals("firstname")) {
			updated = UsersService.updateFirstnameById(updateInfoTemplate);
		} else if (updateInfoTemplate.getType().equals("lastname")) {
			updated = UsersService.updateLastnameById(updateInfoTemplate);
		} else {
			updated = UsersService.updateEmailById(updateInfoTemplate);
		}

		if (updated) {
			Users user = UsersService.findUserById(updateInfoTemplate);
			res.getWriter().println(om.writeValueAsString(user));
		} else {
			res.setStatus(204);
		}
	}

	public static void processAllReimburseso(HttpServletRequest request, HttpServletResponse res) throws IOException {
		List<Reimbursement> list = UsersService.getAllReimburses();
		res.getWriter().println(om.writeValueAsString(list));
	}

	public static void processDecision(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get data from request
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println(body + "kankan");
		// convert json to java obj
		Decision decision = om.readValue(body, Decision.class);
		System.out.println(decision + "kankan");

		// send data to UsersService using make decision and return true or false
		boolean result = UsersService.makeDecision(decision);

		// if true do something and false do something
		if (result) {
			response.setStatus(200);
		} else {
			response.setStatus(204);
		}
	}

	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		List<Users> list = UsersService.findAllAccounts();
		List<Users> employeesList = new ArrayList<>();
		for (Users u : list) { 
			if (u.getUser_role().getUser_role_id() == 1) {
				employeesList.add(u);
			}
		}
		response.getWriter().println(om.writeValueAsString(employeesList));
	}

	//
}
