package com.bookverse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookverse.config.ApiResponse;
import com.bookverse.dto.ReviewDTO;
import com.bookverse.repository.ReviewRepository;
import com.bookverse.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    
     
	
	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService=reviewService;
		
	}
	
	
	@PostMapping("/book/{bookId}")
	public ResponseEntity<ReviewDTO> add(@PathVariable Long bookId, @RequestBody ReviewDTO reviewDTO) {

		return ResponseEntity.ok(reviewService.addReview(bookId,reviewDTO));

	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<List<ReviewDTO>> getReviewsByBook(@PathVariable Long bookId){
		
		return ResponseEntity.ok(reviewService.getReviewsByBook(bookId));
	}
	
	
	@PutMapping("/book/{bookId}")
	public ResponseEntity<ReviewDTO> update(@PathVariable Long bookId, ReviewDTO reviewDTO){
		
		return ResponseEntity.ok(reviewService.updateReview(bookId,reviewDTO));
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long bookId){
		
		return ResponseEntity.ok(reviewService.deleteReview(bookId));
		
	}

}
