package com.financemanagement.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financemanagement.demo.dto.TransactionRequest;
import com.financemanagement.demo.entity.Categories;
import com.financemanagement.demo.entity.SpendTypes;
import com.financemanagement.demo.entity.TransactionType;
import com.financemanagement.demo.entity.Transactions;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.repository.CategoriesRepository;
import com.financemanagement.demo.repository.ExpenseRepository;
import com.financemanagement.demo.repository.SpendTypeRepository;
import com.financemanagement.demo.repository.TransactionTypeRepository;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.util.RoleCheckUtil;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoriesRepository catRepo;
	
	@Autowired
	private SpendTypeRepository spendRepo;

	@Autowired
	private TransactionTypeRepository transactionTypeRepo;


	@Autowired
	private RoleCheckUtil roleCheckUtil;
	public List<Transactions> getExpenseByHomeId(Long id) throws RuntimeException{
		List<Transactions> expenses = expenseRepo.findByHomeId(id);
		if (expenses != null) {
			return expenses;
		}
		else {
			throw new RuntimeException("not Found");
		}
	}
	

	public List<Transactions> getExpenseByHomeIdAndSpendTypeId(Long homeId, Long spendTypeId) {
		List<Transactions> expenses = expenseRepo.findByHomeIdAndSpendTypeId(homeId,spendTypeId);
		if (expenses != null) {
			return expenses;
		}
		else {
			throw new RuntimeException("Data not Found");
		}
	}
	
	public ResponseEntity<Transactions> deleteExpense(Long homeId,Long transactionId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(email);
		long userId = user.getId();

		if (roleCheckUtil.roleVerifyForExpense(userId, homeId)) {
			Transactions transaction = new Transactions();
			transaction = expenseRepo.findById(transactionId).get();
			expenseRepo.deleteById(transactionId);
			return new ResponseEntity<Transactions>(transaction, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	//creating an expense
	public ResponseEntity<Transactions> createTransaction(TransactionRequest request, Long homeId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user1 = userRepo.findByEmail(email);
		Long userId = user1.getId();

		if (roleCheckUtil.roleVerifyForExpense(userId, homeId)) {

			Transactions saveTransaction = new Transactions();
			Optional<Categories> cat = catRepo.findById(request.getCategoryId());
			Optional<SpendTypes> stype = spendRepo.findById(request.getSpendTypeId());
			Optional<TransactionType> tType = transactionTypeRepo.findById(request.getTransactionTypeId());

			saveTransaction.setCategory(cat.get());
			saveTransaction.setSpendType(stype.get());
			saveTransaction.setTransactionType(tType.get());
			saveTransaction.setAmount(request.getAmount());
			saveTransaction.setCreatedBy(request.getcreatedBy());
			saveTransaction.setDescription(request.getDescription());
			saveTransaction.setHomeId(homeId);
			saveTransaction.setTransactionDate(request.getTransactionDate());
			saveTransaction.setTransactionTime(request.getTransactionTime());
			saveTransaction.setExpenseTo(request.getExpenseTo());
			saveTransaction.setUpdatedBy(user1.getName());

			expenseRepo.save(saveTransaction);

			return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public ResponseEntity<Transactions> updateTransaction(Long transactionId,Long homeId,
			TransactionRequest request) throws Exception {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByEmail(email);
		long userId = user.getId();
		String updatedBy = user.getName();

		if (roleCheckUtil.roleVerifyForExpense(userId, homeId)) {
			Transactions saveTransaction = expenseRepo.findById(transactionId)
					.orElseThrow(() -> new Exception("Not found transaction with id = " + transactionId));

			Categories cat = catRepo.findById(request.getCategoryId()).get();
			SpendTypes stype = spendRepo.findById(request.getSpendTypeId()).get();
			TransactionType tType = transactionTypeRepo.findById(request.getTransactionTypeId()).get();

			saveTransaction.setCategory(cat);
			saveTransaction.setSpendType(stype);
			saveTransaction.setTransactionType(tType);
			saveTransaction.setAmount(request.getAmount());
			saveTransaction.setCreatedBy(request.getcreatedBy());
			saveTransaction.setDescription(request.getDescription());
			saveTransaction.setHomeId(homeId);
			saveTransaction.setUpdatedBy(updatedBy);
			saveTransaction.setTransactionDate(request.getTransactionDate());
			saveTransaction.setTransactionTime(request.getTransactionTime());

			expenseRepo.save(saveTransaction);
			return new ResponseEntity<>(saveTransaction, HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	public Transactions getExpensesById(long transactionId) throws Exception {
		Transactions transaction = expenseRepo.findById(transactionId)
				.orElseThrow(() -> new Exception("Not found Comment with id = " + transactionId));

		return transaction;
	}
}
