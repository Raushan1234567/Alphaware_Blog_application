package com.alphabare.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphabare.exception.UserAllreadyRegisteredException;
import com.alphabare.model.User;
import com.alphabare.repository.UserRepository;
import com.alphabare.serviceinterface.UserServiceInterface;


@Service
public class UserServiceImplementation implements UserServiceInterface{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		User user1= userRepository.findByEmail(user.getEmail());
		if(user1!=null) {
			throw new UserAllreadyRegisteredException("User allreadyexists by this name");
		}
		
		return userRepository.save(user);
	}

}
