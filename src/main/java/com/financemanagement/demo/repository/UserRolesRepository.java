package com.financemanagement.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long>{

	List<UserRoles> findByDesc(String description);
}
