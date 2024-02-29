package com.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.models.TransactionSummary;

public interface TransactionRepository extends JpaRepository<TransactionSummary,Integer> 
{
	@Query("from TransactionSummary where accountNo=:arg")
	List<TransactionSummary> getSummaryList(@Param("arg") int an);
}
