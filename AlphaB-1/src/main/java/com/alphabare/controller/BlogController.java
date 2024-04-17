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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.alphabare.model.BlogPost;
import com.alphabare.model.BlogUpdateRequest;
import com.alphabare.model.User;
import com.alphabare.serviceinterface.BlogServiceInterface;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
	
	
	@Autowired
	private BlogServiceInterface blogServiceInterface;
	
	@PostMapping("/post")
    public ResponseEntity<BlogPost> postBlog(@RequestBody BlogPost blog, @RequestParam("userId") Integer userId) {
        // Assuming User ID is passed as a request parameter
        User user = new User();
        user.setUserId(userId);
        BlogPost savedBlog = blogServiceInterface.postByUserId(blog, user);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }

    @GetMapping("/get/allblog")
    public ResponseEntity<List<BlogPost>> getAllBlogs() {
        List<BlogPost> blogs = blogServiceInterface.getAllBlog();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer blogId) {
    	blogServiceInterface.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @GetMapping("/viewByBlogId/{blogId}")
    public ResponseEntity<BlogPost> viewByBlogId(@PathVariable Integer blogId) {
    	BlogPost viewedBlog = blogServiceInterface.viewBlog(blogId); 
        return new ResponseEntity<>(viewedBlog, HttpStatus.OK); 
    }

    
    @GetMapping("/searchblogByHeading")
    public ResponseEntity<List<BlogPost>> viewBlog(@RequestParam String blogHeading){
		return new ResponseEntity<List<BlogPost>>(blogServiceInterface.viewPost(blogHeading),HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/searchblogByCategory")
    public ResponseEntity<List<BlogPost>> viewBlogByCategory(@RequestParam String category){
		return new ResponseEntity<List<BlogPost>>(blogServiceInterface.viewPostByCategory(category),HttpStatus.ACCEPTED);
    	
    }
    
    @PatchMapping("/{blogId}/update")
    public ResponseEntity<BlogPost> updateBlogById(@PathVariable Integer blogId,
                                               @RequestBody BlogUpdateRequest request) {
        BlogPost updatedBlog = blogServiceInterface.updateById(request, blogId);
        return ResponseEntity.ok(updatedBlog);
    }
    
    
    

}
