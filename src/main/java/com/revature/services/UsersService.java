package com.revature.services;

import java.util.List;
import com.revature.dao.UsersDao;
import com.revature.dao.UsersDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.Users;


public class UsersService {
	public static UsersDao uDao = new UsersDaoImpl();
	
	// this method esed to confirm the esername and password if it's correct
	public static Users confirmLogin(String typeOfUser, String username, String password) {
		System.out.println("进入confirmLogin()");
		// using username to search the data
		Users user = findByUsername(username);
		
		System.out.println("查看仙子的" + user);
		// if not found return null
		if (user == null) {
			System.out.println("用户名或密码不正确");
			return null;
		}

		// if found we compare the password and type of the user
		if (user.getUserpassword().equals(password) && user.getUser_role().getUser_role().equals(typeOfUser)) {
			System.out.println("登陆成功");
			return user;
		} else {
			System.out.println("找到用户名，但是密码错误或类型错误");
			return null;
		}
	}

	// this method used to get all account and return the employee match to the username we provided
	public static Users findByUsername(String username) {
		List<Users> all = uDao.findAllAccounts();
		for (Users u : all) { 
			if (u.getUsername().equals(username)) {
				return u; 
			}
		}
		return null;
	}

	// this method will find all the account inside the database
	public static List<Users> findAllAccounts() {
		List<Users> allUsers = uDao.findAllAccounts();
		return allUsers;
	}

	public static List<Reimbursement> getReimburseById(int user_id) {
		List<Reimbursement> list = uDao.getReimburseById(user_id);
		return list;
	}
}
