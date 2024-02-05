package com.sra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sra.entities.Course;
import com.sra.services.CourseServices;
//role dene ke liye


@RestController
@RequestMapping("/Api")
public class MyController {
	
	@Autowired
	private CourseServices courseServices;
	
	@GetMapping("/home")
	public String home()
	{
		return "Hello Akshay";
	}
	
	// get the  courses
	
	@GetMapping("/courses")
	public List<Course> getCourses()
	{
		return this.courseServices.getCourses();
	}
	@GetMapping("/courses/{courseId}")
	public Course getcCourse(@PathVariable("courseId") int courseId)
	{
		return this.courseServices.getCourse(courseId);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course)
	{
		return this.courseServices.addCourse(course);
	}
	
	
//	//get single Book handler
//	@GetMapping("/books/{id}")
//	public ResponseEntity<Book> getBook(@PathVariable("id") int id)
//	{
//		Book book = bookService.getBookId(id);
//		if(book == null)
//		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//		return ResponseEntity.of(Optional.of(book));
//	}
//	
//	//new book handler
//	@PostMapping("/books")
//	public ResponseEntity<Book> addBook(@RequestBody Book book)
//	{
//		Book b = null;
//		try {
//			b = this.bookService.addBook(b);
//			System.out.println(book);
//			return ResponseEntity.of(Optional.of(b));
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
//	 Delete Book handler
//	@DeleteMapping("/courses/{id}")
//	public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) 
//	{
//		try {
//			this.courseServices.deleteCourse(id);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		}
//	}
	
//	// Update book handler
//	@PutMapping("/books/{id}")
//	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id)
//	{
//		try {
//			this.bookService.updateBook(book, id);
//			return ResponseEntity.ok().body(book);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		
//	}
}
