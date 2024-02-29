package com.banking.services;

import com.banking.models.User;

public interface UserService 
{
	int createUser(User user);
	User getUser(String userid);
}
