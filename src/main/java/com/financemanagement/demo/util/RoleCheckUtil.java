package com.financemanagement.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financemanagement.demo.entity.UserHome;
import com.financemanagement.demo.entity.UserRoles;
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
//		System.out.println("Roleeeeeeeeeeeeeeeeeeeeeee"+ userHome.getRoleId());
		long role = 0;
		String role1 = "";
		if (userHome != null) {
//			role = roleRepo.findById(userHome.getRoleId()).get();
			role = userHome.getRoleId();
//			role1 = role.getDesc();
			System.out.println("Roleeeee"+ role1);
		}
		if (homeRepo.existsByIdAndUserId(homeId, userId)) {
			System.out.println("--------------------------"+ "true");
			return true;
		}
		if (role == 2) {
			System.out.println("------------------------"+ "true");
			return true;
		}
		System.out.println("------------------------"+ "false");
		return false;
	}
	
}
