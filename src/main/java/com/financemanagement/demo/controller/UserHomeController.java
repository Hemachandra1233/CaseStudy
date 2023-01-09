package com.financemanagement.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.dto.UserModel;
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
import com.financemanagement.demo.service.UserHomeService;
import com.financemanagement.demo.util.RoleCheckUtil;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class UserHomeController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HomeRepository homeRepo;

	@Autowired
	private UserRolesRepository roleRepo;

	@Autowired
	private UserHomeRepository userHomeRepo;

	@Autowired
	private UserHomeService userHomeService;

	/// Assigning A Role
	@PostMapping("/assigning/{homeId}")
	public ResponseEntity<UserHome> assigningRole(@RequestBody RoleAssignRequest request,
			@PathVariable(name = "homeId") long homeId) throws Exception {

		return userHomeService.assigningRole(request, homeId);
	}

	/// Updating assigned Role
	@PutMapping("/roleupdate/{homeId}")
	public ResponseEntity<UserHome> updatingAssignedRole(@RequestBody RoleAssignRequest request,
			@PathVariable(name = "homeId") Long homeId) {

		return userHomeService.updatingAssignedRole(request, homeId);
	}

	// checkAssignedByHomeIdAndAssigneeId
	@GetMapping("userHomes/{homeId}/{assigneeId}")
	public UserHome getAllUserHomes(@PathVariable(name = "homeId") Long homeId,
			@PathVariable(name = "assigneeId") Long assigneeId) {
		return userHomeService.getAllUserHomesByAssignedByHomeIdAndAssigneeId(homeId, assigneeId);
	}

	// Deleting assigned home
	@DeleteMapping("/deleteAssignee/{homeId}/{assigneeId}")
	public ResponseEntity<UserHome> deletingAssignedHome(@PathVariable(name = "homeId") long homeId,
			@PathVariable(name = "assigneeId") long assigneeId) {

		return userHomeService.deletingAssignedHome(homeId, assigneeId);
	}

	@GetMapping("/userHomes")
	public List<UserHome> getUserHomes() {
		return userHomeRepo.findAll();
	}

	@GetMapping("/expenseTo/{homeId}")
	public List<expenseTo> getExpenseToByHomeIdandUserId(@PathVariable(name = "homeId") long homeId) {

		return userHomeService.getExpenseToByHomeIdandUserId(homeId);
	}

	// Getting All Assigned To Homes by HomeId
	@GetMapping("/assignedTo/{homeId}")
	public List<AssignedTo> getAssignedToBy(@PathVariable(name = "homeId") long homeId) {
		return userHomeService.getAssignedToByHomeId(homeId);
	}

	@GetMapping("/assignedTo2/{userId}")
	public List<AssignedTo> getAssignedToByAssigneeId(@PathVariable(name = "userId") long userId) {
		List<UserHome> userHome = userHomeRepo.findByAssigneeId(userId);
		List<AssignedTo> assignedTo = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
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

}
