package com.financemanagement.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financemanagement.demo.entity.UserRoles;
import com.financemanagement.demo.repository.UserRolesRepository;

@Service
public class RolesService {

	@Autowired
	private UserRolesRepository rolesRepo;
	
	public List<UserRoles> getAllRoles() {
		return rolesRepo.findAll();
	}
}
