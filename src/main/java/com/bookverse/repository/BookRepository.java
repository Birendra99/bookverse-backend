package com.bookverse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookverse.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	Page<Book> findByTitleContainingIgnoreCase(String search, PageRequest pageable);

	Page<Book> findByAuthorContainingIgnoreCase(String search, PageRequest pageable);

	Page<Book> findByGenreContainingIgnoreCase(String search, PageRequest pageable);

}
