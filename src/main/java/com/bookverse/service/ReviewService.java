package com.bookverse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookverse.config.ApiResponse;
import com.bookverse.dto.ReviewDTO;
import com.bookverse.entity.Book;
import com.bookverse.entity.Review;
import com.bookverse.repository.BookRepository;
import com.bookverse.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ModelMapper modelMapper;
	private final ReviewRepository reviewRepository;

	private final BookRepository bookRepository;

	// here constructor injection is happen
	public ReviewService(ModelMapper modelMapper, ReviewRepository reviewRepository, BookRepository bookRepository) {

		this.modelMapper = modelMapper;
		this.bookRepository = bookRepository;
		this.reviewRepository = reviewRepository;
	}

	public ReviewDTO addReview(Long bookId, ReviewDTO reviewDTO) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		Review review = modelMapper.map(reviewDTO, Review.class);

		review.setBook(book);

		Review saved = reviewRepository.save(review);
		updateBookAvgRating(book);

		ReviewDTO responseDto = modelMapper.map(saved, ReviewDTO.class);

		responseDto.setBookId(bookId);

		return responseDto;
	}

	public void updateBookAvgRating(Book book) {
		List<Review> reviews = reviewRepository.findByBookId(book.getId());

		/*
		 * double avg = reviews.stream() .mapToDouble(Review::getRating) .average()
		 * .orElse(0.0); This uses Java Stream API to:
		 * 
		 * Loop through all reviews.
		 * 
		 * Extract the rating from each review.
		 * 
		 * Calculate the average.
		 * 
		 * If there are no reviews, .orElse(0.0) ensures the average is 0.0.
		 * 
		 * ✔️ In our example:
		 * 
		 * Ratings: 5.0, 3.0, 4.0
		 * 
		 * Average = (5 + 3 + 4) / 3 = 4.0
		 */
		// double avg = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
		
		double avg= reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
		
		book.setAverageRating(avg);
		bookRepository.save(book);

	}

	public ReviewDTO updateReview(Long bookId, ReviewDTO reviewDTO) {

		Review review = reviewRepository.findById(reviewDTO.getId())
				.orElseThrow(() -> new RuntimeException("Review Not Found."));
		review.setComments(reviewDTO.getComments());
		review.setRating(reviewDTO.getRating());

		Review updated = reviewRepository.save(review);

		updateBookAvgRating(review.getBook());

		ReviewDTO response = modelMapper.map(updated, ReviewDTO.class);
		response.setBookId(updated.getBook().getId());

		return response;
	}

	public ApiResponse deleteReview(Long bookId) {

		Review reviews = reviewRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Review Not Found."));

		Book book = reviews.getBook();

		reviewRepository.deleteById(bookId);

		updateBookAvgRating(book);

		return new ApiResponse("Review Deleted", true);

	}

	public List<ReviewDTO> getReviewsByBook(Long bookId) {

		return reviewRepository.findByBookId(bookId).stream().map(r -> {
			ReviewDTO dto = modelMapper.map(r, ReviewDTO.class);
			dto.setBookId(bookId);
			return dto;
		}).collect(Collectors.toList());
	}

}
