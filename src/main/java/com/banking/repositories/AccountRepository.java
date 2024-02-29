package com.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.models.Account;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account,Integer> 
{
	Account findByUserid(String uid);
	@Query("select a.amount from Account a where a.accountNo=:arg")
	int getAmount(@Param("arg") int accountno);
	
	@Transactional
	@Modifying
	@Query("update Account set amount=amount+:arg1 where accountNo=:arg2")
	void updateAmount(@Param("arg2") int an,@Param("arg1") int amount);
	
	
	@Query("select userid  from Account where accountNo=:arg")
	String isAccountExist(@Param("arg") int accountNo);
}
