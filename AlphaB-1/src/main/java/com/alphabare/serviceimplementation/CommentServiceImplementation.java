package com.alphabare.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphabare.exception.BlogNotFoundForThisId;
import com.alphabare.exception.CommentNotFoundByThisdId;
import com.alphabare.model.BlogPost;
import com.alphabare.model.Comment;
import com.alphabare.repository.BlogRepository;
import com.alphabare.repository.CommentRepository;
import com.alphabare.serviceinterface.CommentInterface;

@Service
public class CommentServiceImplementation implements CommentInterface{

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment addComment(Integer postId, Comment comment) {
	   
	    Optional<BlogPost> optionalBlogPost = blogRepository.findById(postId);
	    if (optionalBlogPost.isEmpty()) {
	        throw new BlogNotFoundForThisId("Blog not found");
	    }
	    
	    BlogPost blogPost = optionalBlogPost.get();
	    
	   
	    comment.setBlogpost(blogPost);
	    
	   
	    blogPost.getCommentList().add(comment);
	    
	  
	    blogRepository.save(blogPost);
	    
	    
	    return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Integer postId, Integer commentId) {
	    
	    Optional<Comment> optionalComment = commentRepository.findById(commentId);
	    if (optionalComment.isEmpty()) {
	        throw new CommentNotFoundByThisdId("Comment not found");
	    }
	    
	    Comment comment = optionalComment.get();
	    if (!(comment.getBlogpost().getBlogId()==postId)) {
	        throw new IllegalArgumentException("The provided comment does not belong to the specified post");
	    }
	   
	  
	    commentRepository.delete(comment);
	}

	@Override
	public List<Comment> getCommentByPostId(Integer postId) {
		
		  Optional<BlogPost> b=blogRepository.findById(postId);
		  
		  if(b.isEmpty()) {
			  throw new BlogNotFoundForThisId("blog not found");
		  }
		  
		  BlogPost blog=b.get();
		  
		    List<Comment> cmlist= blog.getCommentList();
		  
		
		return cmlist;
	}

	@Override
	public Comment updateComment(Comment updatedComment, Integer commentId) {
	    
	    Comment existingComment = commentRepository.findById(commentId)
	                                                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId));
	    
	    
	    existingComment.setComment(updatedComment.getComment()); 

	   
	    return commentRepository.save(existingComment);
	}


	
	
	
	


}
