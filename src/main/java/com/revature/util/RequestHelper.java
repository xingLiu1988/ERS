package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginTemplate;
import com.revature.models.Reimbursement;
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
		Users u = UsersService.confirmLogin(loginAttempt.getTypeOfUser(),loginAttempt.getUsername(), loginAttempt.getPassword());
		
		
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
	
	// This method's purpose is to return all Employees from the DB in JSON form 
	public static void processReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 设置数据类型
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
		Users user = om.readValue(body, Users.class);
		
		// 获取reimbursement
		List<Reimbursement> reimbursements = UsersService.getReimburseById(user.getUser_id());
		
		// 当不是NULL的时候传回数据
		if(reimbursements != null) {
			res.setContentType("application/json");
			res.getWriter().println(om.writeValueAsString(reimbursements));
		}else {
			res.setStatus(204);
		}
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
}
