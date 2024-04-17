package com.alphabare.serviceimplementation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphabare.exception.BlogNotFoundForThisHeading;
import com.alphabare.exception.BlogNotFoundForThisId;
import com.alphabare.exception.UserNotExistByThisIdException;
import com.alphabare.model.BlogPost;
import com.alphabare.model.BlogUpdateRequest;
import com.alphabare.model.User;
import com.alphabare.repository.BlogRepository;
import com.alphabare.repository.UserRepository;
import com.alphabare.serviceinterface.BlogServiceInterface;


@Service
public class BlogServiceImplementation implements BlogServiceInterface{

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public BlogPost postByUserId(BlogPost blog, User user) {
	
		Optional<User> user1=userRepository.findById(user.getUserId());
		
		if(user1.isEmpty()) {
			throw new UserNotExistByThisIdException("User not exist by this id");
		}
		
		blog.setUser(user);
         		
		return blogRepository.save(blog);
	}

	@Override
	public List<BlogPost> getAllBlog() {
	
		return blogRepository.findAll();
	}

	@Override
	public void deleteBlog(Integer blogId) {
		
            Optional<BlogPost> b= blogRepository.findById(blogId);
            if(b.isEmpty())
            {		
            	throw new BlogNotFoundForThisId("Blog Not Found");
            }
            blogRepository.deleteById(blogId);
		
		
	}
	
	
	public List<BlogPost> viewPost(String blogHeading) {
	   
	    String searchTerm = blogHeading.toLowerCase();
	    
	    List<BlogPost> blogs = blogRepository.findAll();
	    List<BlogPost> matchedBlogs = new ArrayList<>();

	    for (BlogPost blog : blogs) {
	       
	        String heading = blog.getBolgHeading().toLowerCase();
	        
	        if (heading.contains(searchTerm)) {
	            matchedBlogs.add(blog);
	        }
	    }

	    if (matchedBlogs.isEmpty()) {
	        throw new BlogNotFoundForThisHeading("Blog not found for this heading");
	    }

	    return matchedBlogs;
	}


	@Override
	public BlogPost viewBlog(Integer blogId) {
		Optional<BlogPost> b= blogRepository.findById(blogId);
        if(b.isEmpty())
        {		
        	throw new BlogNotFoundForThisId("Blog Not Found");
        }
        
        BlogPost b1=b.get();
        int views=b1.getNoOfViews()+1;
        b1.setNoOfViews(views);
        blogRepository.save(b1);
		return b1;
	}

	@Override
	public BlogPost updateById(BlogUpdateRequest request, Integer blogId) {
		// TODO Auto-generated method stub
		Optional<BlogPost> b= blogRepository.findById(blogId);
		if(b.isEmpty()) {
			throw new BlogNotFoundForThisId("Blog not found for this id");
		}
		
		BlogPost blog = b.get();
	    blog.setBolgHeading(request.getBlogHeading());
	    blog.setBolgContent(request.getBlogContent());

	    blogRepository.save(blog);

	    return blog;
	}
	
	
	
	@Override
	public List<BlogPost> getAllPostsByToday() {
	LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
	LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
	return blogRepository.findByPostedDateBetween(startOfDay, endOfDay);
	}

	@Override
	public List<BlogPost> viewPostByCategory(String category) {
String searchTerm = category.toLowerCase();
	    
	    List<BlogPost> blogs = blogRepository.findAll();
	    List<BlogPost> matchedBlogs = new ArrayList<>();

	    for (BlogPost blog : blogs) {
	       
	        String categ = blog.getCategory().toLowerCase();
	        
	        if (categ.contains(searchTerm)) {
	            matchedBlogs.add(blog);
	        }
	    }

	    if (matchedBlogs.isEmpty()) {
	        throw new BlogNotFoundForThisHeading("Blog not found for this category");
	    }

	    return matchedBlogs;
	}

	@Override
	public List<BlogPost> getAllPostsByUserId(int userId) {
	List<BlogPost> postList = blogRepository.findAllByUserUserId(userId);
	if(!postList.isEmpty()) {
	return postList;
	}else {
	throw new BlogNotFoundForThisId("Post not available");
	}
	}

}
