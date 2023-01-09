package com.financemanagement.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financemanagement.demo.dto.ResponseByYear;
import com.financemanagement.demo.entity.Transactions;

public interface ExpenseRepository extends JpaRepository<Transactions, Long>{

//	Optional<Transactions> findByHomeId(Long HomeId);

	List<Transactions> findByHomeIdAndSpendTypeId(Long homeId, Long spendTypeId);

	List<Transactions> findByHomeId(Long homeId);

	List<Transactions> findByCategoryId(Long categoryId);
//	List<Transactions> findByDateBetween(Date startDate,Date endDate);
	
	
	@Query(nativeQuery = true, value="select * from TRANSACTIONS_TBL c where c.HOME_ID =:homeId and c.TRANSACTION_DATE between :startDate-1 and :endDate+1")
	List<Transactions> getData_between(@Param("homeId") Long homeID, @Param("startDate") Date date, @Param("endDate") Date date2);
	
	@Query(nativeQuery = true, value = "SELECT * FROM SUNNYHC.TRANSACTIONS_TBL C WHERE C.HOME_ID =:homeId AND EXTRACT(Year FROM C.TRANSACTION_DATE) =:year ORDER BY EXTRACT(MONTH FROM C.TRANSACTION_DATE)")
	List<Transactions> getExpensesByYear(@Param("homeId") Long homeId,  @Param("year") Long year);
	
//	SELECT EXTRACT(MONTH FROM C.TRANSACTION_DATE) AS MONTH,SUM(C.AMOUNT) AS AMOUNT FROM SUNNYHC.TRANSACTIONS_TBL C WHERE C.HOME_ID = 70 AND EXTRACT(Year FROM C.TRANSACTION_DATE) = 2022 GROUP BY EXTRACT(MONTH FROM C.TRANSACTION_DATE);
	
	@Query(nativeQuery = true, value = "SELECT EXTRACT(MONTH FROM C.TRANSACTION_DATE) AS MONTH,SUM(C.AMOUNT) AS AMOUNT,C.SPEND_TYPE_ID AS TYPE FROM SUNNYHC.TRANSACTIONS_TBL C WHERE C.HOME_ID =:homeId AND EXTRACT(Year FROM C.TRANSACTION_DATE) =:year GROUP BY EXTRACT(MONTH FROM C.TRANSACTION_DATE),C.SPEND_TYPE_ID")
	List<ResponseByYear> getExpensesByYear2(@Param("homeId") Long homeId,  @Param("year") Long year);
	
	
//	@NamedNativeQuery(name = "Transactions.getExpensesByYear2",
//			query = "SELECT EXTRACT(MONTH FROM C.TRANSACTION_DATE) AS MONTH,SUM(C.AMOUNT) AS AMOUNT FROM TRANSACTIONS_TBL C WHERE C.HOME_ID =:homeId AND EXTRACT(Year FROM C.TRANSACTION_DATE) =:year GROUP BY EXTRACT(MONTH FROM C.TRANSACTION_DATE)",
//			resultSetMapping = "Mapping.ResponseByYear")
//			@SqlResultSetMapping(name = "Mapping.ResponseByYear",
//					classes = @ConstructorResult(targetClass = ResponseByYear.class,
//												 columns = {@ColumnResult(name = ""),
//														 	@ColumnResult(name = "")}))
}



