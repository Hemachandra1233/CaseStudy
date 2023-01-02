package com.financemanagement.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.financemanagement.demo.entity.Categories;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.repository.CategoriesRepository;
import com.financemanagement.demo.repository.UserRepository;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository catRepo;

	@Autowired
	private UserRepository userRepo;

	public Categories createCategory(Categories category) throws Exception {

		long tempUserId = category.getUserId();
		Categories savedcat = null;

		if (userRepo.findById(tempUserId) != null) {
			savedcat = catRepo.save(category);
		} else {
			throw new Exception("Create User to enter categories");
		}
		System.out.println("saved cat id -->" + savedcat.getId());
		return savedcat;
	}

	public List<Categories> getAllCategories(Long userId) {
		return catRepo.findByUserId(userId);
	}

	public Categories createCat(Categories category) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long userId = user1.getId();

		Categories cat = new Categories();
		cat.setUserId(userId);
		cat.setDescription(category.getDescription());
		cat.setCreatedBy(category.getCreatedBy());
		catRepo.save(cat);
		return cat;
	}
}
