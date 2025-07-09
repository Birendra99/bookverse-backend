package com.bookverse.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private Long id;
	
	private String username;
	
	private String comments;
	
	private double rating;
	
	private Long bookId;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
