package com.alphabare.serviceinterface;

import java.util.List;

import com.alphabare.model.User;

public interface UserServiceInterface {

	User registerUser(User user);

	List<User> getAllUsers();

	

	User getAllUsersByEmail(String email);

}
