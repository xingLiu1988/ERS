package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.UserRole;
import com.revature.models.Users;
import com.revature.util.ConnectionUtil;

public class UsersDaoImpl implements UsersDao{
	
	private static Logger log = Logger.getLogger(UsersDaoImpl.class);
	
	@Override
	public List<Users> findAllAccounts() {
		List<Users> allAccounts = new ArrayList<>();
		Users users;
		UserRole userRole;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM users INNER JOIN user_role ON users.user_role_id = user_role.user_role_id";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet r = p.executeQuery();
			while(r.next()) {
				userRole = new UserRole(r.getInt("user_role_id"), r.getString("user_role"));
				users = new Users(r.getInt("user_id"), r.getString("username"), r.getString("userpassword"), r.getString("first_name"), r.getString("last_name"), r.getString("email"), userRole);
				allAccounts.add(users);
			}
		}catch (SQLException e) {
			log.warn("SQLException");
		}
		return allAccounts;
	}

}
