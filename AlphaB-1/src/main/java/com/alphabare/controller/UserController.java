package com.alphabare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphabare.model.User;
import com.alphabare.repository.UserRepository;
import com.alphabare.serviceinterface.UserServiceInterface;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserServiceInterface serviceInterface;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registers")
	ResponseEntity<User> registerUser(@RequestBody  User user){
		user.setPassword( passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<User>(serviceInterface.registerUser(user),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/getAllusers")
	public ResponseEntity<List<User>> getAllUser(){
		
		return new ResponseEntity<List<User>>(serviceInterface.getAllUsers(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllUserByEmail/{email}")
	public ResponseEntity<User> getAllUserByEmail(@PathVariable String email){
		
		return new ResponseEntity<User>(serviceInterface.getAllUsersByEmail(email),HttpStatus.ACCEPTED);
	}
	
	 @GetMapping("/loginuser")
		public ResponseEntity<String> logInUserHandler(Authentication auth){
			 User opt= repository.findByEmail(auth.getName());
			 if(opt==null) throw new RuntimeException("No user found") ;
			 User user = opt;
			 return new ResponseEntity<>(user.getUserName()+" Logged In Successfully", HttpStatus.ACCEPTED);
		}       
	
	
	

}
