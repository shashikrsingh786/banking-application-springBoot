package com.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.models.User;

public interface UserRepository extends JpaRepository<User,String> 
{
}
