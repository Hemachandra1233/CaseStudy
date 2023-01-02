package com.financemanagement.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financemanagement.demo.entity.Transactions;

public interface ExpenseRepository extends JpaRepository<Transactions, Long>{

//	Optional<Transactions> findByHomeId(Long HomeId);

	List<Transactions> findByHomeIdAndSpendTypeId(Long homeId, Long spendTypeId);

	List<Transactions> findByHomeId(Long homeId);

	List<Transactions> findByCategoryId(Long categoryId);
//	List<Transactions> findByDateBetween(Date startDate,Date endDate);
	
	
	@Query(nativeQuery = true, value="select * from TRANSACTIONS_TBL c where c.HOME_ID =:homeId and c.TRANSACTION_DATE between :startDate-1 and :endDate+1")
	List<Transactions> getData_between(@Param("homeId") Long homeID, @Param("startDate") Date date, @Param("endDate") Date date2);
}


