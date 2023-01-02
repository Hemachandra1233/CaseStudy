package com.financemanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.entity.SpendTypes;
import com.financemanagement.demo.service.SpendTypesService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SpendTypeController {
	
	@Autowired
	private SpendTypesService spendTypesService;
	
	@PostMapping("/createspendtype")
	public SpendTypes createSpendType(@RequestBody SpendTypes spendTypes) {
		
		return spendTypesService.createSpendTypes(spendTypes);
		
	}
	@GetMapping("/spendtypes")
	public List<SpendTypes> getSpendTypes() {
		return spendTypesService.getAllSpendtypes();
	}

}
