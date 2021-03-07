package com.revature.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.models.Users;

class UsersDaoImplTest {
	UsersDao uDao = new UsersDaoImpl();
	
	@Test
	void testFindAllAccounts() {
		List<Users> allUsers = uDao.findAllAccounts();
		System.out.println(allUsers);
	}

}
