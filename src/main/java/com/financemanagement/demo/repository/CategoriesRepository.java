package com.financemanagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long>{

	List<Categories> findByUserId(Long userId);
	
	Categories findByDescription(String description);
}
