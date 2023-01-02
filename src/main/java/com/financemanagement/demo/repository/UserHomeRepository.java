package com.financemanagement.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.UserHome;

public interface UserHomeRepository extends JpaRepository<UserHome, Long>{

	List<UserHome> findByAssigneeId(Long assigneeId);

	UserHome findByHomeIdAndAssigneeId(Long homeId, Long assigneeId);

	List<UserHome> findByHomeId(Object object);
	
	UserHome findByHomeIdAndAssignerId(Long homeId, Long assignerId);
	
	boolean existsById(Long Id);
	
	boolean existsByHomeIdAndAssigneeId(Long homeId, Long assigneeId);
	
	void deleteByHomeId(Long homeId);

//	void deleteAllByHomeIdInBatch(Long homeId);
}
