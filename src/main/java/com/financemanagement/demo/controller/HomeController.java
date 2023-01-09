package com.financemanagement.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financemanagement.demo.entity.GetHomes;
import com.financemanagement.demo.entity.Home;
import com.financemanagement.demo.service.HomeService;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
	
	@Autowired
	private HomeService homeService;

//	@PreAuthorize("hasRole('ROLE_Owner')")
	@GetMapping("/get")
	public Object get() {
		
//		return SecurityContextHolder.getContext().getAuthentication().getName();
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	

	// get all assigned homes
	@GetMapping("/homes1/assigned/{assigneeId}")
	public ResponseEntity<List<Home>> getAllHomesByAssigneeId(@PathVariable(value = "assigneeId") Long assigneeId)
			throws Exception {

		return homeService.getAllHomesByAssigneeId(assigneeId);
	}

//	@GetMapping("/homes/{id}")
//	public ResponseEntity<Home> getHomesByUserId(@PathVariable(value = "id") Long id) throws Exception {
//		Home home = homeRepo.findById(id).orElseThrow(() -> new Exception("Not found Comment with id = " + id));
//		return new ResponseEntity<>(home, HttpStatus.OK);
//	}

	//used for creating home
	@PostMapping("/createhome")
	public ResponseEntity<Home> createHome(@RequestBody Home homeRequest)
			throws Exception {
		
		return homeService.createHome(homeRequest);
	}

	// used for getting all homes
	@GetMapping("/homes12")
	public ResponseEntity<List<GetHomes>> getAllHomesByUserId()
			throws Exception {
		
		return homeService.getAllHomesByUserId();
	}

	//used for updating home
	@PutMapping("updateHome/{homeId}")
	public ResponseEntity<Home> updatingHome(@RequestBody Home request,
			@PathVariable(name = "homeId") Long homeId) throws Exception {
		return homeService.updatingHome(request, homeId);
	}
	
	//used for soft deleting home
	@DeleteMapping("delete/{homeId}")
	public ResponseEntity<Home> deleteByHomeId(@PathVariable(name = "homeId") Long homeId) {
		return homeService.deleteByHomeId(homeId);
	}
}
