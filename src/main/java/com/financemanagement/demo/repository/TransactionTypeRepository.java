package com.financemanagement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financemanagement.demo.entity.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>{

}
