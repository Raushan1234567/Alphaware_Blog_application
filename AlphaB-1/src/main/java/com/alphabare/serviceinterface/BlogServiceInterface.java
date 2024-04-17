package com.alphabare.serviceinterface;

import java.util.List;

import com.alphabare.model.BlogPost;
import com.alphabare.model.BlogUpdateRequest;
import com.alphabare.model.User;

public interface BlogServiceInterface {
 
	
	BlogPost postByUserId(BlogPost blog,User userId);
	
	
	List<BlogPost> viewPost(String blogHeading);
	
    List<BlogPost> getAllBlog();
	
	void deleteBlog(Integer blogId);
	
	


	BlogPost  viewBlog(Integer blogId);


	BlogPost updateById(BlogUpdateRequest blogrequest, Integer blogId);


	List<BlogPost> getAllPostsByToday();


	List<BlogPost> viewPostByCategory(String category);
}
