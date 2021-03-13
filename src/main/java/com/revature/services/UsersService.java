package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.UsersDao;
import com.revature.dao.UsersDaoImpl;
import com.revature.models.Decision;
import com.revature.models.ReimburseTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.UpdateInfoTemplate;
import com.revature.models.Users;


public class UsersService {
	public static UsersDao uDao = new UsersDaoImpl();
	private static Logger log = Logger.getLogger(UsersService.class);
	// this method esed to confirm the esername and password if it's correct
	public static Users confirmLogin(String typeOfUser, String username, String password) {
		// using username to search the data
		Users user = findByUsername(username);
		
		// if not found return null
		if (user == null) {
			log.info("User is not found");
			return null;
		}

		// if found we compare the password and the type of the user
		if (user.getUserpassword().equals(password) && user.getUser_role().getUser_role().equals(typeOfUser)) {
			return user;
		} else {
			return null;
		}
	}

	// this method used to get all accounts and return the employee match to the username we provided
	public static Users findByUsername(String username) {
		List<Users> all = uDao.findAllAccounts();
		for (Users u : all) { 
			if (u.getUsername().equals(username)) {
				return u; 
			}
		}
		return null;
	}

	// this method will find all the users account inside the database
	public static List<Users> findAllAccounts() {
		List<Users> allUsers = uDao.findAllAccounts();
		return allUsers;
	}

	public static List<Reimbursement> getReimburseById(int user_id) {
		return uDao.getReimburseById(user_id);
	}

	public static boolean insertReimburse(ReimburseTemplate reimburse) {
		return uDao.insertReimburse(reimburse);
	}

	public static boolean updateFirstnameById(UpdateInfoTemplate updateInfoTemplate) {
		return uDao.updateFirstnameById(updateInfoTemplate);
	}

	public static boolean updatePasswordById(UpdateInfoTemplate updateInfoTemplate) {
		return uDao.updatePasswordById(updateInfoTemplate);
	}

	public static boolean updateLastnameById(UpdateInfoTemplate updateInfoTemplate) {
		return uDao.updateLastnameById(updateInfoTemplate);
	}

	public static boolean updateEmailById(UpdateInfoTemplate updateInfoTemplate) {
		return uDao.updateEmailById(updateInfoTemplate);
	}

	public static Users findUserById(UpdateInfoTemplate updateInfoTemplate) {
		List<Users> list = uDao.findAllAccounts();
		for (Users u : list) { 
			if(u.getUser_id() == updateInfoTemplate.getUserId()) {
				return u;
			}
		}
		return null;
	}

	public static List<Reimbursement> getAllReimburses() {
		return uDao.getAllReimbuses();
	}

	public static boolean makeDecision(Decision decision) {
		return uDao.makeDecision(decision);
	}
}
