package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
//	@RequestMapping(value = "/books",method = RequestMethod.GET)
//	@GetMapping("/books")
//	public Book getBooks()
//	{
//		Book book = new Book();
//		book.setId(1);
//		book.setTitle("Java Book");
//		book.setAuthor("xyz");
//		
//		return book;
//	}
	
	//get All books handler
	
	@GetMapping("/books")
	public List<Book> getBooks()
	{
		return this.bookService.getAllBooks();
	}
	
	// Send code get all books handler
//	@GetMapping("/books")
//	public ResponseEntity<List<Book>> getBooks()
//	{
//		List<Book> list = bookService.getAllBooks();
//		if(list.size()<=0)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//		return ResponseEntity.of(Optional.of(list));
//	}
	
	// get single book handler
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id)
	{
		Book book = bookService.getBookId(id);
		if(book==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		//return bookService.getBookId(id);
		return ResponseEntity.of(Optional.of(book));
	}
	
	//new book handler
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		Book b = null;
		try {
			 b = this.bookService.addBook(book);
			 System.out.println(book);
			 return ResponseEntity.of(Optional.of(b));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// delete book handler
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId)
	{
		try
		{
			this.bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	//Update book handler
	@PutMapping("/books/{bookId}")
	public Book updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId)
	{
		this.bookService.updateBook(book,bookId);
		return book;
	}
}
