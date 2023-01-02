package com.financemanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.entity.Categories;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.repository.CategoriesRepository;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.service.CategoriesService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriesController {
	
	@Autowired
	private CategoriesService catService;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/categories")
	public List<Categories> getCategories(){
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 =  userRepo.findByEmail(email);
		Long userId = user1.getId();
		
		return catService.getAllCategories(userId);
	}
	
	@PostMapping("/createCategory")
	public Categories createCat(@RequestBody Categories category) {
		return catService.createCat(category);
	}


}
