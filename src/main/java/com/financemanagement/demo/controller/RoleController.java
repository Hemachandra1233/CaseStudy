package com.financemanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.entity.UserRoles;
import com.financemanagement.demo.repository.UserRolesRepository;
import com.financemanagement.demo.service.RolesService;

@RestController
public class RoleController {

	@Autowired
	private RolesService rolesService;
	@GetMapping("/roles")
	public List<UserRoles> getAllRoles() {
		return rolesService.getAllRoles();
	}
}
