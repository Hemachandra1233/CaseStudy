package com.financemanagement.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financemanagement.demo.entity.AssignedTo;
import com.financemanagement.demo.entity.Home;
import com.financemanagement.demo.entity.RoleAssignRequest;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.entity.UserHome;
import com.financemanagement.demo.entity.expenseTo;
import com.financemanagement.demo.repository.HomeRepository;
import com.financemanagement.demo.repository.UserHomeRepository;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.repository.UserRolesRepository;
import com.financemanagement.demo.util.RoleCheckUtil;

@Service
public class UserHomeService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HomeRepository homeRepo;

	@Autowired
	private UserRolesRepository roleRepo;

	@Autowired
	private UserHomeRepository userHomeRepo;

	@Autowired
	private RoleCheckUtil roleCheckUtil;
	
	// Assigning a role
	public ResponseEntity<UserHome> assigningRole(RoleAssignRequest request,long homeId) throws Exception {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long assignerId = user1.getId();
		if (roleCheckUtil.roleVerify(assignerId, homeId)) {
			if (userRepo.findByEmail(request.getEmail()) == null) {
				throw new Exception("User Does Not Exists");
			} else {
				if (userHomeRepo.findByHomeIdAndAssigneeId(homeId,
						userRepo.findByEmail(request.getEmail()).getId()) != null) {
					throw new Exception("This Home Already Assigned to User");
				} else {
					UserHome userHome = new UserHome();
					userHome.setAssigneeId(userRepo.findByEmail(request.getEmail()).getId());
					userHome.setHomeId(homeId);
					userHome.setAssignerId(assignerId);
					userHome.setRoleId(request.getRoleId());
					return new ResponseEntity<UserHome>(userHomeRepo.save(userHome), HttpStatus.CREATED);
				}
			}
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public ResponseEntity<UserHome> updatingAssignedRole(RoleAssignRequest request, Long homeId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long assignerId = user1.getId();

		if (roleCheckUtil.roleVerify(assignerId, homeId)) {
			UserHome userHome = userHomeRepo.findByHomeIdAndAssigneeId(homeId,
					userRepo.findByEmail(request.getEmail()).getId());
			userHome.setAssigneeId(userRepo.findByEmail(request.getEmail()).getId());
			userHome.setHomeId(homeId);
			userHome.setAssignerId(assignerId);
			userHome.setRoleId(request.getRoleId());

			return new ResponseEntity<UserHome>(userHomeRepo.save(userHome), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	// gettingbyAssignedByHomeIdAndAssigneeId
	public UserHome getAllUserHomesByAssignedByHomeIdAndAssigneeId(Long homeId, Long assigneeId) {
		return userHomeRepo.findByHomeIdAndAssigneeId(homeId, assigneeId);
	}
	
	//Deleting Assigned home
	public ResponseEntity<UserHome> deletingAssignedHome(long homeId, long assigneeId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long assignerId = user1.getId();
		if (roleCheckUtil.roleVerify(assignerId, homeId)) {
			UserHome userHome = new UserHome();
			userHome = userHomeRepo.findByHomeIdAndAssigneeId(homeId, assigneeId);

			userHomeRepo.deleteById(userHome.getId());
			return new ResponseEntity<UserHome>(userHome, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	// Getting all assigned homes
	public List<AssignedTo> getAssignedToByHomeId(long homeId) {
		List<UserHome> userHome = userHomeRepo.findByHomeId(homeId);
		List<AssignedTo> assignedTo = new ArrayList<>();
		for (int i = 0; i < userHome.size(); i++) {
			User user = userRepo.findById(userHome.get(i).getAssigneeId()).get();
			Home home = homeRepo.findById(userHome.get(i).getHomeId()).get();

			AssignedTo assignedTo1 = new AssignedTo();
			assignedTo1.setUserId(user.getId());
			assignedTo1.setName(user.getName());
			assignedTo1.setEmail(user.getEmail());
			assignedTo1.setRoleId(userHome.get(i).getRoleId());
			assignedTo1.setRole(roleRepo.findById(userHome.get(i).getRoleId()).get().getDesc());
			assignedTo1.setHomeName(home.getDescription());
			assignedTo1.setHomeId(home.getId());
			assignedTo.add(assignedTo1);
		}
		return assignedTo;
	}
	//Getting all expense To
	public List<expenseTo> getExpenseToByHomeIdandUserId(long homeId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user12 = userRepo.findByEmail(email);
		Long userId = user12.getId();

		List<UserHome> userHome = userHomeRepo.findByHomeId(homeId);
		List<expenseTo> expenseTo = new ArrayList<>();
		User user1 = userRepo.findById(userId).get();
		expenseTo expenseTo2 = new expenseTo();
		expenseTo2.setId(userId);
		expenseTo2.setName(user1.getName());
		expenseTo.add(expenseTo2);
		for (int i = 0; i < userHome.size(); i++) {
			User user = userRepo.findById(userHome.get(i).getAssigneeId()).get();
			expenseTo expenseTo1 = new expenseTo();
			expenseTo1.setId(user.getId());
			expenseTo1.setName(user.getName());
			expenseTo.add(expenseTo1);
		}
		return expenseTo;

	}
}
