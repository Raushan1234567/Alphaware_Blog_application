package com.alphabare.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphabare.model.BlogPost;

public interface BlogRepository extends JpaRepository<BlogPost, Integer>{
	
	List<BlogPost> findByPostedDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
	
	List<BlogPost> findBybolgHeading(String blogHeading);
	
	

}
