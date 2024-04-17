package com.alphabare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphabare.model.BlogPost;
import com.alphabare.serviceinterface.BlogServiceInterface;

@RestController
@RequestMapping("/api/blog")
public class TodayBlogController {
	
	
	
	@Autowired
	private BlogServiceInterface blogServiceInterface;
	
	
	@GetMapping("/bytoday")
	public ResponseEntity<List<BlogPost>> getPostsCreatedToday() {
	List<BlogPost> posts = blogServiceInterface.getAllPostsByToday();
	return new ResponseEntity<>(posts, HttpStatus.OK);
	}

}
