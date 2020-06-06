package com.bookshopapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshopapp.entities.Book;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentConsumerService {
	@Autowired
	ProviderConnerctor providerConnector;

	public   Optional<Book> getBook(Long id)   {
        Optional<Book> bookHolder = Optional.empty();
        try {
        	bookHolder = Optional.ofNullable(providerConnector
                    .serializeData(providerConnector.getData(String.format("/book/%d", id)).getBody(), Book.class));
        } catch (Exception e) {
            e.printStackTrace();
            return bookHolder;
        }
        return bookHolder;
	}
}
