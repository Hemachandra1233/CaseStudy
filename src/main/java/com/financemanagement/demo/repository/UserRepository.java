package com.financemanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, long password);

	public User findByEmailAndPassword(String email, String password);
	
	Boolean existsByEmail(String email);

	public User findByName(String name);

}
