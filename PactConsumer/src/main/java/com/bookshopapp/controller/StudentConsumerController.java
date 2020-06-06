package com.bookshopapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bookshopapp.entities.Book;
import com.bookshopapp.service.StudentConsumerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class StudentConsumerController {
	@Autowired
	private StudentConsumerService StudentConsumerService;

	@GetMapping("consumer")
	public ResponseEntity<String> keepAlive() {
		return ResponseEntity.ok("Keep_alive");
	}

	@GetMapping("book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") Long id) throws IOException {

		return StudentConsumerService.getBook(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}

}
