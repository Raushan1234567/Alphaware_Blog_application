package com.alphabare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphabare.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
//	List<Comment> findCommentByPostId(Integer blogId);

}
