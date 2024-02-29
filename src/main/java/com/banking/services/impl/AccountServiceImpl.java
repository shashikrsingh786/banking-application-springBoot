package com.banking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.repositories.AccountRepository;
import com.banking.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired
	private AccountRepository accountRepository;

	public int getAmount(int an) 
	{
		return accountRepository.getAmount(an);
	}
}
