package com.revature.dao;

import java.util.List;
import com.revature.models.Users;

public interface UsersDao {
	public List<Users> findAllAccounts();
}
