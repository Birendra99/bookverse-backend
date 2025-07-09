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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookverse.config.ApiResponse;
import com.bookverse.dto.BookDTO;
import com.bookverse.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	// create book

	@PostMapping
	public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {

		return ResponseEntity.ok(bookService.createBook(bookDTO));
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks(@RequestParam(required = false, defaultValue = "") String search,
			@RequestParam(required = false, defaultValue = "") String field, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {

		// books?search=harry&field=title&page=0&size=5

		return ResponseEntity.ok(bookService.getAllBook(search,field,page,size));

	}

	// get book by id

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {

		return ResponseEntity.ok(bookService.getBookById(bookId));
	}

//	@PutMapping("/{bookId}")
//	public ResponseEntity<BookDTO> update(@PathVariable Long bookId) {
//
//		return ResponseEntity.ok(bookService.update(bookId));
//	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<BookDTO> update(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
		return ResponseEntity.ok(bookService.update(bookId, bookDTO));
	}

    @DeleteMapping("/{bookId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long bookId) {

		return ResponseEntity.ok(bookService.delete(bookId));
	}

}
