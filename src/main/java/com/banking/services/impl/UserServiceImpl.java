package com.banking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.repositories.AccountRepository;
import com.banking.repositories.UserRepository;
import com.banking.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired private UserRepository userRepository;
	@Autowired private AccountRepository accountRepository;

	public int createUser(User user) 
	{
		userRepository.save(user);
		Account account=new Account();
		account.setUserid(user.getUserid());
		accountRepository.save(account);
		return account.getAccountNo();
	}
	public User getUser(String userid) 
	{
		User user=userRepository.findById(userid).orElse(null);
		return user;
	}
}
