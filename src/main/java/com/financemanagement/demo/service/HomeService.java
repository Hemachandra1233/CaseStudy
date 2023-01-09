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

import com.financemanagement.demo.entity.GetHomes;
import com.financemanagement.demo.entity.Home;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.entity.UserHome;
import com.financemanagement.demo.exception.ItemAlreadyExistsException;
import com.financemanagement.demo.repository.HomeRepository;
import com.financemanagement.demo.repository.UserHomeRepository;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.util.RoleCheckUtil;

@Service
public class HomeService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HomeRepository homeRepo;

	@Autowired
	private UserHomeRepository userHomeRepo;

	@Autowired
	private RoleCheckUtil roleCheckUtil;

	// Getting all Homes By UserId
	public ResponseEntity<List<GetHomes>> getAllHomesByUserId() throws Exception {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
//		String[] id = email.split("_");
//		Long varLong=Long.parseLong(id[1]);
//		System.out.println("idddddddddddddd"+varLong);
		if (userRepo.findByEmail(email) == null) {
			throw new Exception("Not found User with id = " + email);
		} else {
			User user = userRepo.findByEmail(email);
			Long userId = user.getId();
			List<Home> homes = homeRepo.findByUserId(userId);
			List<GetHomes> getHomes = new ArrayList<>();
			for (int i = 0; i < homes.size(); i++) {
				if (homes.get(i).getStatus() == 1) {
					GetHomes getHomes1 = new GetHomes();
					getHomes1.setId(homes.get(i).getId());
					getHomes1.setDescription(homes.get(i).getDescription());
					getHomes1.setShowUpdate(true);
					getHomes1.setStatus(homes.get(i).getStatus());

					getHomes.add(getHomes1);
				}
			}

			List<UserHome> userHome = userHomeRepo.findByAssigneeId(userId);
			if (userHome == null) {
				return new ResponseEntity<List<GetHomes>>(getHomes, HttpStatus.OK);
			} else {
				for (int i = 0; i < userHome.size(); i++) {
					Home homes2 = homeRepo.findById(userHome.get(i).getHomeId()).get();
					if (homes2.getStatus() == 1) {
						GetHomes getHomes2 = new GetHomes();
						getHomes2.setId(homes2.getId());
						getHomes2.setDescription(homes2.getDescription());
						getHomes2.setShowUpdate(false);
						getHomes2.setStatus(homes2.getStatus());

						getHomes.add(getHomes2);
					}
				}
				return new ResponseEntity<List<GetHomes>>(getHomes, HttpStatus.OK);
			}
		}
	}

	// creating home for a user
	public ResponseEntity<Home> createHome(Home homeRequest) throws Exception {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long userId = user1.getId();
		Home home = userRepo.findById(userId).map(user -> {
			if (homeRepo.findByDescription(homeRequest.getDescription()) != null) {
				throw new ItemAlreadyExistsException("Home Name Already Exists");
			} else {
				homeRequest.setUser(user);
				homeRequest.setStatus((long) 1);
			}
			return homeRepo.save(homeRequest);
		}).orElseThrow(() -> new Exception("Not found User with id = " + userId));

		return new ResponseEntity<>(home, HttpStatus.CREATED);
	}

	// Updating Home
	public ResponseEntity<Home> updatingHome(Home request, Long homeId) throws Exception {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(email);
		Long userId = user.getId();

		if (roleCheckUtil.roleVerify(userId, homeId)) {
			Home home = homeRepo.findById(homeId).get();

			home.setDescription(request.getDescription());
			home.setCreatedBy(request.getCreatedBy());
			return new ResponseEntity<Home>(homeRepo.save(home), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	// Soft deleting home
	public ResponseEntity<Home> deleteByHomeId(Long homeId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(email);
		Long userId = user.getId();
		if (roleCheckUtil.roleVerify(userId, homeId)) {
			Home home = homeRepo.findById(homeId).get();
			homeRepo.deleteById(homeId);
			return new ResponseEntity<Home>(home, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	// Get all your assigned homes
	public ResponseEntity<List<Home>> getAllHomesByAssigneeId(Long assigneeId)
			throws Exception {

		List<Home> homes = new ArrayList<>();
		List<UserHome> userHome = userHomeRepo.findByAssigneeId(assigneeId);
		if (userHome == null) {
			return new ResponseEntity<>(homes, HttpStatus.OK);
		} else {
			for (int i = 0; i < userHome.size(); i++) {
				Home homes2 = homeRepo.findById(userHome.get(i).getHomeId()).get();
				homes.add(homes2);
			}
			return new ResponseEntity<>(homes, HttpStatus.OK);
		}
	}
}
