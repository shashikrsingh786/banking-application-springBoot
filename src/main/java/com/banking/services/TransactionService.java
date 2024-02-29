package com.banking.services;

import java.util.List;

import com.banking.models.TransactionSummary;

public interface TransactionService
{
	void depositAmount(int amount, int an);
	List<TransactionSummary> getList(int an);
	void withdrawAmount(int amount, int an);
}
