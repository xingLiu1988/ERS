package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimburseTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.UpdateInfoTemplate;
import com.revature.models.UserRole;
import com.revature.models.Users;
import com.revature.util.ConnectionUtil;

public class UsersDaoImpl implements UsersDao {

	private static Logger log = Logger.getLogger(UsersDaoImpl.class);

	@Override
	public List<Users> findAllAccounts() {
		List<Users> allAccounts = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM users INNER JOIN user_role ON users.user_role_id = user_role.user_role_id";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				UserRole userRole = new UserRole(r.getInt("user_role_id"), r.getString("user_role"));
				Users users = new Users(r.getInt("user_id"), r.getString("username"), r.getString("userpassword"),
						r.getString("first_name"), r.getString("last_name"), r.getString("email"), userRole);
				allAccounts.add(users);
			}
		} catch (SQLException e) {
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
			while (r.next()) {
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
		} catch (SQLException e) {
			log.warn("SQLException");
		}
		return list;
	}

	@Override
	public boolean insertReimburse(ReimburseTemplate reimburse) {
		boolean isInserted = false;
		// amount
		// description
		// type_id 1 = lodging, 2 = travel, 3 = food, 4 = other
		// status_id = 1 pending 默认
		// user_id
		double amount = reimburse.getAmount();
		String description = reimburse.getDescription();
		String typeValue = reimburse.getType();
		int type = 0;
		if (typeValue.equals("lodging")) {
			type = 1;
		} else if (typeValue.equals("travel")) {
			type = 2;
		} else if (typeValue.equals("food")) {
			type = 3;
		} else {
			type = 4;
		}
		int id = reimburse.getUser_id();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbursement(amount, description, type_id, status_id, user_id) VALUES(?,?,?,1,?)";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setDouble(1, amount);
			p.setString(2, description);
			p.setInt(3, type);
			p.setInt(4, id);
			int executeUpdate = p.executeUpdate();
			if (executeUpdate == 1) {
				isInserted = true;
				System.out.println("成功插入一条数据");
			}
		} catch (SQLException e) {
			System.out.println("sqlexception inside the insertReimburse dao");
			e.printStackTrace();
		}

		return isInserted;
	}

	@Override
	public boolean updateFirstnameById(UpdateInfoTemplate updateInfoTemplate) {
		boolean updated = false;

		int userId = updateInfoTemplate.getUserId();
		String updateString = updateInfoTemplate.getupdatedValue();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE users SET first_name = ? WHERE user_id = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, updateString);
			p.setInt(2, userId);
			int executeUpdate = p.executeUpdate();
			if (executeUpdate == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			log.warn("SQLException");
		}
		return updated;
	}

	@Override
	public boolean updateLastnameById(UpdateInfoTemplate updateInfoTemplate) {
		boolean updated = false;

		int userId = updateInfoTemplate.getUserId();
		String updateString = updateInfoTemplate.getupdatedValue();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE users SET last_name = ? WHERE user_id = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, updateString);
			p.setInt(2, userId);
			int executeUpdate = p.executeUpdate();
			if (executeUpdate == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			log.warn("SQLException");
		}
		return updated;

	}

	@Override
	public boolean updateEmailById(UpdateInfoTemplate updateInfoTemplate) {
		boolean updated = false;

		int userId = updateInfoTemplate.getUserId();
		String updateString = updateInfoTemplate.getupdatedValue();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE users SET email = ? WHERE user_id = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, updateString);
			p.setInt(2, userId);
			int executeUpdate = p.executeUpdate();
			if (executeUpdate == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			log.warn("SQLException");
		}
		return updated;
	}

	@Override
	public boolean updatePasswordById(UpdateInfoTemplate updateInfoTemplate) {
		boolean updated = false;

		int userId = updateInfoTemplate.getUserId();
		String updateString = updateInfoTemplate.getupdatedValue();

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE users SET userpassword = ? WHERE user_id = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, updateString);
			p.setInt(2, userId);
			int executeUpdate = p.executeUpdate();
			if (executeUpdate == 1) {
				updated = true;
			}
		} catch (SQLException e) {
			log.warn("SQLException");
		}
		return updated;
	}



}
