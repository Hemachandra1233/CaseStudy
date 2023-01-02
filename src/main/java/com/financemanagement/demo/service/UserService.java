package com.financemanagement.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.financemanagement.demo.dto.UserLogin;
import com.financemanagement.demo.dto.UserModel;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.entity.UserRoles;
import com.financemanagement.demo.exception.ItemAlreadyExistsException;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.repository.UserRolesRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired UserRolesRepository rolesRepo;
	
	public User saveUser(UserModel user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("email already exists" + user.getEmail());
		}
		List<UserRoles> roles = rolesRepo.findByDesc("Owner");
		User newUser = new User();
		newUser.setRoles(roles);
		BeanUtils.copyProperties(user, newUser);
		return userRepository.save(newUser);
	}
	
	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User fetchUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public ResponseEntity<?> loginUser(UserLogin userLogin) {
		User user = userRepository.findByEmail(userLogin.getEmail());
		if (user.getPassword().equals(userLogin.getPassword())) {
			return ResponseEntity.ok(user);
		}
		else {
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
	}
	
	public User getLogin(UserLogin userLogin) {
		return userRepository.findByEmail(userLogin.getEmail());
	}
	
	public List<User> getUser(){
		return userRepository.findAll();
	}
}
