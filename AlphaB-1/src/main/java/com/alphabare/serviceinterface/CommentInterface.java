package com.alphabare.serviceinterface;

import java.util.List;

import com.alphabare.model.Comment;

public interface CommentInterface {
	
	
	
	
	void deleteComment(Integer postId,Integer commentId);

	Comment addComment(Integer postId, Comment comment);

	List<Comment> getCommentByPostId(Integer postId);

	Comment updateComment(Comment comment, Integer commentId);

	
		

}
