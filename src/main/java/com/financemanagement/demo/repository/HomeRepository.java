package com.financemanagement.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.Home;

public interface HomeRepository extends JpaRepository<Home, Long>{

	List<Home> findByUserId(Long userId);
	
	List<Home> findByIdAndUserId(long Id, long userId);

	@Transactional
	void deleteByUserId(long userId);
	
	boolean existsByIdAndUserId(long Id, long userId);
	
	Home findByDescription(String description);
}
