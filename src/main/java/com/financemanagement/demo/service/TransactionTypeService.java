package com.financemanagement.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financemanagement.demo.entity.TransactionType;
import com.financemanagement.demo.repository.TransactionTypeRepository;

@Service
public class TransactionTypeService {
	
	@Autowired
	private TransactionTypeRepository repo;
	
	public List<TransactionType> getType() {
		return repo.findAll();
	}


}
