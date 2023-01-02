package com.financemanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.entity.TransactionType;
import com.financemanagement.demo.service.TransactionTypeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionTypesController {
	
	@Autowired
	private TransactionTypeService service;
	
	@GetMapping("/transactiontypes")
	public List<TransactionType> getTransactionTypes() {
		return service.getType();
	}

}
