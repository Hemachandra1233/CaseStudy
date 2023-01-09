package com.financemanagement.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financemanagement.demo.dto.ResponseByYear;
import com.financemanagement.demo.dto.TransactionRequest;
import com.financemanagement.demo.dto.TransactionResponse;
import com.financemanagement.demo.entity.Bills;
import com.financemanagement.demo.entity.Categories;
import com.financemanagement.demo.entity.Home;
import com.financemanagement.demo.entity.SpendTypes;
import com.financemanagement.demo.entity.TransactionType;
import com.financemanagement.demo.entity.Transactions;
import com.financemanagement.demo.entity.User;
import com.financemanagement.demo.repository.BillsRepository;
import com.financemanagement.demo.repository.CategoriesRepository;
import com.financemanagement.demo.repository.ExpenseRepository;
import com.financemanagement.demo.repository.SpendTypeRepository;
import com.financemanagement.demo.repository.TransactionTypeRepository;
import com.financemanagement.demo.repository.UserRepository;
import com.financemanagement.demo.service.ExpenseService;
import com.financemanagement.demo.util.RoleCheckUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

	public static String DIRECTORY = System.getProperty("user.home") + "/Downloads/";

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private BillsRepository billsRepo;

	@Autowired
	private ExpenseRepository expenseRepo;

	@GetMapping("/expenses/{homeId}")
	public List<TransactionResponse> getAllExpensesByHomeId(@PathVariable Long homeId) throws RuntimeException {
		List<Transactions> transaction = expenseService.getExpenseByHomeId(homeId);
		List<TransactionResponse> response = new ArrayList<>();

		for (int i = 0; i < transaction.size(); i++) {
			TransactionResponse response2 = new TransactionResponse();
			response2.setAmount(transaction.get(i).getAmount());
			response2.setTransactionId(transaction.get(i).getTransactionId());
			response2.setTransactionDate(transaction.get(i).getTransactionDate());
			response2.setExpenseTo(transaction.get(i).getExpenseTo());
			response2.setDescription(transaction.get(i).getDescription());
			response2.setCategory(transaction.get(i).getCategory());
			response2.setSpendType(transaction.get(i).getSpendType());
			response2.setTransactionType(transaction.get(i).getTransactionType());
			response2.setHomeId(transaction.get(i).getHomeId());
			response2.setCreatedBy(transaction.get(i).getCreatedBy());
			response2.setCreatedOn(transaction.get(i).getCreatedOn());
			response2.setUpdatedBy(transaction.get(i).getUpdatedBy());
			response2.setUpdatedOn(transaction.get(i).getUpdatedOn());
			response2.setCreatedOnTime(transaction.get(i).getCreatedOn().toLocaleString().substring(13));
			response2.setUpdatedOnTime(transaction.get(i).getUpdatedOn().toLocaleString().substring(13));

			response.add(response2);
		}
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>" + transaction.get(10).getCreatedOn().toLocaleString().substring(12));
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>" + transaction.get(0).getCreatedOn());
//		System.out.println(">>>>>>>>>>>>>>>>>>>>> Id" + transaction.get(10).getTransactionId());
//		System.out.println(">>>>>>>>>>>>>>>>>>>>> desc" + transaction.get(10).getDescription());
//		System.out.println(">>>>>>>>>>>>>>>>>>>>> sizeeee" + transaction.size());
		return response;
	}

	@GetMapping("/expenses/{homeId}/spendTypeId/{spendTypeId}")
	public List<Transactions> getallExpensesByHomeIdAndSpendTypeId(@PathVariable(name = "homeId") Long homeId,
			@PathVariable(name = "spendTypeId") Long spendTypeId) {
		return expenseService.getExpenseByHomeIdAndSpendTypeId(homeId, spendTypeId);
	}

	@DeleteMapping("/deleteexpense/{homeId}/{transactionId}")
	public ResponseEntity<Transactions> deleteExpense(@PathVariable(name = "transactionId") Long transactionId,
			@PathVariable(name = "homeId") long homeId) throws IOException {

		ResponseEntity<Transactions> transaction = expenseService.deleteExpense(homeId, transactionId);

		String DIRECTORY1 = DIRECTORY + transactionId;
		System.out.printf("Path Issssssssssssssssssssssssssssss" + DIRECTORY1);

		File file = new File(DIRECTORY1);
		FileUtils.deleteDirectory(file);

		file.delete();

		List<Bills> bills = billsRepo.findByTransactionId(transactionId);

		for (int i = 0; i < bills.size(); i++) {
			billsRepo.deleteById(bills.get(i).getId());
		}
		return transaction;

	}

	// Used for entering expense
	@PostMapping("/expenses1/add/{homeId}")
	public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionRequest request,
			@PathVariable(name = "homeId") Long homeId) {

		return expenseService.createTransaction(request, homeId);
	}

	// Used for updating expense
	@PutMapping("/expenses2/{transactionId}/{homeId}")
	public ResponseEntity<Transactions> updateTransaction(@PathVariable Long transactionId, @PathVariable Long homeId,
			@RequestBody TransactionRequest request) throws Exception {

		return expenseService.updateTransaction(transactionId, homeId, request);
	}

	// in update flow
	@GetMapping("expenses/transactionId/{transactionId}")
	public Transactions getExpensesById(@PathVariable("transactionId") long transactionId) throws Exception {

		return expenseService.getExpensesById(transactionId);
	}

	@GetMapping("/fetch/{homeId}/{one_date}/{two_date}")
	public List<Transactions> getData_between(@PathVariable(name = "homeId") Long homeId,
			@PathVariable(value = "one_date") Date fromDate, @PathVariable(value = "two_date") Date toDate) {
		System.out.println("sizeeeeeeeeeee " + expenseRepo.getData_between(homeId, fromDate, toDate).size());
		return expenseRepo.getData_between(homeId, fromDate, toDate);
	}

	@GetMapping("/expenses/{homeId}/{one_date}/{two_date}")
	public List<TransactionResponse> getAllExpensesByHomeIdBetweenDates(@PathVariable Long homeId,
			@PathVariable(value = "one_date") Date fromDate, @PathVariable(value = "two_date") Date toDate)
			throws RuntimeException {
		List<Transactions> transaction = expenseRepo.getData_between(homeId, fromDate, toDate);
		List<TransactionResponse> response = new ArrayList<>();

		for (int i = 0; i < transaction.size(); i++) {
			TransactionResponse response2 = new TransactionResponse();
			response2.setAmount(transaction.get(i).getAmount());
			response2.setTransactionId(transaction.get(i).getTransactionId());
			response2.setTransactionDate(transaction.get(i).getTransactionDate());
			response2.setExpenseTo(transaction.get(i).getExpenseTo());
			response2.setDescription(transaction.get(i).getDescription());
			response2.setCategory(transaction.get(i).getCategory());
			response2.setSpendType(transaction.get(i).getSpendType());
			response2.setTransactionType(transaction.get(i).getTransactionType());
			response2.setHomeId(transaction.get(i).getHomeId());
			response2.setCreatedBy(transaction.get(i).getCreatedBy());
			response2.setCreatedOn(transaction.get(i).getCreatedOn());
			response2.setUpdatedBy(transaction.get(i).getUpdatedBy());
			response2.setUpdatedOn(transaction.get(i).getUpdatedOn());
			response2.setCreatedOnTime(transaction.get(i).getCreatedOn().toLocaleString().substring(13));
			response2.setUpdatedOnTime(transaction.get(i).getUpdatedOn().toLocaleString().substring(13));

			response.add(response2);
		}
		return response;
	}
	
	
	@GetMapping("/expensess/{homeId}/{year}")
	public List<ResponseByYear> getDataByYear(@PathVariable(name = "homeId") Long homeId,
			@PathVariable(value = "year") Long year) {
//		System.out.println("sizeeeeeeeeeee " + expenseRepo.getData_between(homeId, fromDate, toDate).size());
//		return expenseRepo.getData_between(homeId, fromDate, toDate);
		return expenseRepo.getExpensesByYear2(homeId,year);
	}
}
