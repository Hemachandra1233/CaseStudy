package com.financemanagement.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.Bills;

public interface BillsRepository extends JpaRepository<Bills, Long> {
	
	List<Bills> findByTransactionId(Long transactionId);
}
