package com.alphabare.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	
	private String bolgHeading;
	
	private String bolgContent;
	
	private int noOfViews;
	
	private String category;
	

	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "blogpost")
	private List<Comment> commentList=new ArrayList<>();
	
    private LocalDateTime postedDate=LocalDateTime.now();
	
	
}
