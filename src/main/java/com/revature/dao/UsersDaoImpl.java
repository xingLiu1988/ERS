package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.UserRole;
import com.revature.models.Users;
import com.revature.util.ConnectionUtil;

public class UsersDaoImpl implements UsersDao{
	
	private static Logger log = Logger.getLogger(UsersDaoImpl.class);
	
	@Override
	public List<Users> findAllAccounts() {
		List<Users> allAccounts = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM users INNER JOIN user_role ON users.user_role_id = user_role.user_role_id";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet r = p.executeQuery();
			while(r.next()) {
				UserRole userRole = new UserRole(r.getInt("user_role_id"), r.getString("user_role"));
				Users users = new Users(r.getInt("user_id"), r.getString("username"), r.getString("userpassword"), r.getString("first_name"), r.getString("last_name"), r.getString("email"), userRole);
				allAccounts.add(users);
			}
		}catch (SQLException e) {
			log.warn("SQLException");
		}
		return allAccounts;
	}

	
	@Override
	public List<Reimbursement> getReimburseById(int user_id) {
		List<Reimbursement> list = new ArrayList<>();
		Reimbursement reimb;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursement re INNER JOIN reimb_type r ON r.type_id = re.type_id INNER JOIN reimb_status rs ON rs.status_id = re.status_id WHERE re.user_id = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, user_id);
			ResultSet r = p.executeQuery();
			while(r.next()) {
				reimb = new Reimbursement();
				reimb.setReimb_id(r.getInt("reimb_id"));
				reimb.setAmount(r.getFloat("amount"));
				reimb.setSubmitted(r.getString("submitted").substring(0, 19));
				reimb.setResolved(r.getString("resolved"));
				reimb.setDescription(r.getString("description"));
				reimb.setStatus(r.getString("status"));
				reimb.setType(r.getString("reimb_type"));
				reimb.setUser_id(r.getInt("user_id"));
				System.out.println("uDao Layer:接收到的数据" + reimb);
				list.add(reimb);
			}
		}catch (SQLException e) {
			log.warn("SQLException");
		}
		return list;
	}

}
