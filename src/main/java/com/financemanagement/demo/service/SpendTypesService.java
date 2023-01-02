package com.financemanagement.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financemanagement.demo.entity.SpendTypes;
import com.financemanagement.demo.repository.SpendTypeRepository;

@Service
public class SpendTypesService {
	
	@Autowired
	SpendTypeRepository spendRepo; 
	
	public SpendTypes createSpendTypes(SpendTypes spendTypes) {
		return spendRepo.save(spendTypes);
	}

	public List<SpendTypes> getAllSpendtypes() {
		return spendRepo.findAll();
	}
}
