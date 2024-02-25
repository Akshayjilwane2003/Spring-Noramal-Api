package com.api.book.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import com.api.book.entities.Book;
import com.api.book.services.dao.BookRepository;

@Component
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
//	private static List<Book> list = new ArrayList<>();
//	
//	static {
//		list.add(new Book(12,"Python","ABC"));
//		list.add(new Book(13,"java","XYZ"));
//		list.add(new Book(14,"c","DEF"));
//		list.add(new Book(15,"c++","GHI"));
//	}
	
	//get all books
	public List<Book> getAllBooks()
	{
		//return list;
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list;
	}
	
	//get single book  by id
	public Book getBookId(int id) 
	{
		Book book = null;
		try {
		//book = list.stream().filter(e->e.getId()==id).findFirst().get();
			book=this.bookRepository.findById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return book;
	}
	
	//Adding the book
	// Adding the book
	public Book addBook(Book b) {
	    // Save the book to the repository and get the result
	    Book result = bookRepository.save(b);

	    // Return the result obtained from the repository
	    return result;
	}

	//delete book
	public void deleteBook(int bid)
	{
//		list = list.stream().filter(book->{if(book.getId()!=bid)
//		{
//			return true;
//		}
//		else {
//			return false;
//		}	
//		}).collect(Collectors.toList());
//		list = list.stream().filter(e->e.getId()!=bid).collect(Collectors.toList());
		bookRepository.deleteById(bid);
	}
	
	// Update the book
	public void updateBook(Book book, int bookId)
	{
//		list = list.stream().map(b->{
//			if(b.getId()==bookId)
//			{
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		
		book.setId(bookId);
		bookRepository.save(book);
	}

}
