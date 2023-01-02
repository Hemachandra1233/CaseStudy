package com.financemanagement.demo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.dto.UserLogin;
import com.financemanagement.demo.dto.UserModel;
//import com.financemanagement.demo.entity.JwtRequest;
import com.financemanagement.demo.entity.JwtResponse;
//import com.financemanagement.demo.entity.Transactions;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.repository.UserRolesRepository;
import com.financemanagement.demo.service.JwtService;
import com.financemanagement.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRolesRepository roleRepo;
	
//	@PostMapping("/register")
//	public User saveUser(@RequestBody User user) throws Exception{
//		String tempEmailId = user.getEmail();
//		if (tempEmailId != null && "".equals(tempEmailId)) {
//			User userobj = userService.fetchUserByEmail(tempEmailId);
//			if (userobj != null) {
//				throw new Exception("user with "+ tempEmailId+ "already exists");
//			}
//		}
//		User userObj = null;
//		userObj = userService.saveUser(user);
//		return userObj;
//	}
//	
	@PostMapping("/login")
//	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmail();
		String tempPass = user.getPassword();
		System.out.println(tempPass);
		User userObj = null;
		if (tempEmailId != null) {
			userObj = userService.fetchUserByEmailAndPassword(tempEmailId, tempPass);
		}
		if (userObj == null) {
			throw new Exception("User does not exists");
		}
		return userObj;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody UserModel user) {
		User user2 = new User();
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/login1")
	public JwtResponse login(@RequestBody UserLogin userLogin) throws Exception{
		return jwtService.createJwtToken(userLogin);
	}
	
//	public JwtResponse createToken(@RequestBody JwtRequest jwtRequest) throws Exception{
//		return jwtService.createJwtToken(jwtRequest);
//	}
	
}
