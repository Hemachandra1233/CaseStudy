package com.financemanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.SpendTypes;

public interface SpendTypeRepository extends JpaRepository<SpendTypes, Long>{

}
