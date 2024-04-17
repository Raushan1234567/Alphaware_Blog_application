package com.alphabare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphabare.model.Comment;
import com.alphabare.serviceinterface.CommentInterface;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentInterface commentInterface;
	
	
	@PostMapping("/addcomment/{blogId}")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment,@PathVariable Integer blogId){
		return new ResponseEntity<Comment>(commentInterface.addComment(blogId, comment),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("bypostId/{postId}")
	public ResponseEntity<List<Comment>> getComment(@PathVariable Integer postId){
		return new ResponseEntity<List<Comment>>(commentInterface.getCommentByPostId(postId),HttpStatus.ACCEPTED);
		
	}
	
	
	
	@PatchMapping("/updatecommentsByCommentId/{commentId}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment,@PathVariable Integer commentId){
		return new ResponseEntity<Comment>(commentInterface.updateComment(comment,commentId),HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/deleteCommentByPostIdAndCommentId/{postId}/{commentId}")
	public ResponseEntity<String> delete(@PathVariable Integer postId,@PathVariable Integer commentId){
		
		commentInterface.deleteComment(postId, commentId);
		
		return new ResponseEntity<String>("SuccessfullyDeletd",HttpStatus.ACCEPTED);
		
	}
}
