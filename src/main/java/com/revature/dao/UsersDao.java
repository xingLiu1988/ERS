package com.revature.dao;

import java.util.List;

import com.revature.models.Decision;
import com.revature.models.ReimburseTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.UpdateInfoTemplate;
import com.revature.models.Users;

public interface UsersDao {
	public List<Users> findAllAccounts();

	public List<Reimbursement> getReimburseById(int user_id);

	public boolean insertReimburse(ReimburseTemplate reimburse);

	public boolean updateFirstnameById(UpdateInfoTemplate updateInfoTemplate);

	public boolean updateLastnameById(UpdateInfoTemplate updateInfoTemplate);

	public boolean updateEmailById(UpdateInfoTemplate updateInfoTemplate);

	public boolean updatePasswordById(UpdateInfoTemplate updateInfoTemplate);

	public List<Reimbursement> getAllReimbuses();

	public boolean makeDecision(Decision decision);

}
