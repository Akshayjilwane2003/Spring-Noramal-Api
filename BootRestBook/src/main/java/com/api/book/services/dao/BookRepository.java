package com.api.book.services.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.book.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
	public Book findById(int id);
}
