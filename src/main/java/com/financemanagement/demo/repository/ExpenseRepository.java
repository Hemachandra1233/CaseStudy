package com.financemanagement.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.Transactions;

public interface ExpenseRepository extends JpaRepository<Transactions, Long>{

//	Optional<Transactions> findByHomeId(Long HomeId);

	List<Transactions> findByHomeIdAndSpendTypeId(Long homeId, Long spendTypeId);

	List<Transactions> findByHomeId(Long homeId);

	List<Transactions> findByCategoryId(Long categoryId);
//	List<Transactions> findByDateBetween(Date startDate,Date endDate);
}
