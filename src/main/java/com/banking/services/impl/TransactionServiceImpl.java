package com.banking.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.models.TransactionSummary;
import com.banking.repositories.AccountRepository;
import com.banking.repositories.TransactionRepository;
import com.banking.services.TransactionService;

@Service  
public class TransactionServiceImpl implements TransactionService 
{
	@Autowired private TransactionRepository transactionRepository;
	@Autowired private AccountRepository accountRepository;

	public void depositAmount(int amount, int an) 
	{
		accountRepository.updateAmount(an,amount);
		updateAmount("credit",an,amount);
	}
	public List<TransactionSummary> getList(int an) 
	{
		return transactionRepository.getSummaryList(an);
	}
	public void withdrawAmount(int amount, int an) 
	{
		accountRepository.updateAmount(an,-amount);
		updateAmount("debit",an,amount);
	}
	private void updateAmount(String type,int an,int amount)
	{
		TransactionSummary transactionSummary=new TransactionSummary();
		transactionSummary.setAccountNo(an);
		transactionSummary.setAmount(amount);
		transactionSummary.setDate(LocalDate.now().toString());
		LocalTime tm=LocalTime.now();
		transactionSummary.setTime(tm.getHour()+":"+tm.getMinute()+":"+tm.getSecond());
		transactionSummary.setTransactionType(type);
		transactionRepository.save(transactionSummary);
	}
}
