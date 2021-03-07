package com.revature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;
import com.revature.models.Users;

class UsersDaoImplTest {
	UsersDao uDao = new UsersDaoImpl();
	
	@Test
	void testFindAllAccounts() {
		List<Users> allUsers = uDao.findAllAccounts();
		System.out.println(allUsers);
	}

	@Test
	void testGetReimburseById() {
		List<Reimbursement> r = uDao.getReimburseById(2);
		System.out.println(r.toString());
	}
}
