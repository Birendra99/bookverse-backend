package com.bookverse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookverse.config.ApiResponse;
import com.bookverse.dto.BookDTO;
import com.bookverse.entity.Book;
import com.bookverse.repository.BookRepository;

@Service
public class BookService {

	private final ModelMapper modelMapper;
	private final BookRepository bookRepository;

	public BookService(ModelMapper modelMapper, BookRepository bookRepository) {
		this.modelMapper = modelMapper;
		this.bookRepository = bookRepository;
	}

	// Creating a book
	public BookDTO createBook(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		if (book.getAverageRating() == null) {
			book.setAverageRating(0.0);
		}
		Book saved = bookRepository.save(book);
		return modelMapper.map(saved, BookDTO.class);
	}

	public List<BookDTO> getAllBook(String search, String field, int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<Book> books;

		if ("title".equalsIgnoreCase(field)) {
			books = bookRepository.findByTitleContainingIgnoreCase(search, pageable);
		} else if ("author".equalsIgnoreCase(field)) {
			books = bookRepository.findByAuthorContainingIgnoreCase(search, pageable);
		} else if ("genre".equalsIgnoreCase(field)) {
			books = bookRepository.findByGenreContainingIgnoreCase(search, pageable);
		} else {
			books = bookRepository.findAll(pageable);
		}

		return books.stream()
			.map(book -> modelMapper.map(book, BookDTO.class))
			.collect(Collectors.toList());
	}

	public BookDTO getBookById(Long bookId) {
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> new RuntimeException("Book with id: " + bookId + " not exist."));
		return modelMapper.map(book, BookDTO.class);
	}

	// ✅ Updated to accept BookDTO as parameter
	public BookDTO update(Long bookId, BookDTO bookDTO) {
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> new RuntimeException("Book with id " + bookId + " is not found."));

		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setSummary(bookDTO.getSummary());

		Book updated = bookRepository.save(book);
		return modelMapper.map(updated, BookDTO.class);
	}

	public ApiResponse delete(Long bookId) {
		bookRepository.deleteById(bookId);
		return new ApiResponse("Deleted", true);
	}
}
