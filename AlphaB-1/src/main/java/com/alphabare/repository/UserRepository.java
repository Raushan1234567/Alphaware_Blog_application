package com.alphabare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphabare.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	
	User findByEmail(String email);
	
}
