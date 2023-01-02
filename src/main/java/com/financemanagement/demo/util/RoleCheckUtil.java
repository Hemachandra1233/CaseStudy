package com.financemanagement.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financemanagement.demo.entity.UserHome;
import com.financemanagement.demo.repository.HomeRepository;
import com.financemanagement.demo.repository.UserHomeRepository;
import com.financemanagement.demo.repository.UserRolesRepository;

@Component
public class RoleCheckUtil {
	
	
	@Autowired
	private HomeRepository homeRepo;
	
	@Autowired
	private UserHomeRepository userHomeRepo;
	
	@Autowired
	private UserRolesRepository roleRepo;
	
	public boolean roleVerify(long userId, long homeId) {
		
		if (homeRepo.existsByIdAndUserId(homeId, userId)) {
			
			return true;
		}
		return false;
	}
	
	public boolean roleVerifyForExpense(long userId, long homeId) {
		
		UserHome userHome = userHomeRepo.findByHomeIdAndAssigneeId(homeId, userId);
		String role = null;
		if (userHome != null) {
			role = roleRepo.findById(userHome.getRoleId()).get().getDesc();
		}
		if (homeRepo.existsByIdAndUserId(homeId, userId)) {
			return true;
		}
		else if(role == "Member") {
			return true;
		}
		return false;
	}
	
}
