package com.alphabare.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list=userRepository.findAll();
		return list;
	}

	@Override
	public User getAllUsersByEmail(String email) {
		// TODO Auto-generated method stub
	User user1=	userRepository.findByEmail(email);
	
	if(user1==null) {
		throw new UsernameNotFoundException("User not found");
	}
	
	
		return user1;
	}

}
