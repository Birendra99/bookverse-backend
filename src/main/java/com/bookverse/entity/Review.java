package com.bookverse.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
     
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 
	 private String username;
	 
	 private String comments;
	 
	 private double rating;
	 
	 // review creation time and updation time
	 
	 @CreationTimestamp
	 private LocalDateTime createdAt;
	 
	 @UpdateTimestamp
	 private LocalDateTime updatedAt;
     
	 @ManyToOne
	 @JoinColumn(name="book_id") // acts as foreign key in review table
     private Book book;
}
