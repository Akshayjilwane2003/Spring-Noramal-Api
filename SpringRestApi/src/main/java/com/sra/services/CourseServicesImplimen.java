package com.sra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sra.entities.Course;
import com.sra.services.dao.CourseRepository;

@Service
public class CourseServicesImplimen implements CourseServices{

	List<Course> list;
	
	
	public CourseServicesImplimen() {
		list = new ArrayList<>();
		list.add(new Course(1,"java Core Course","this course contain basic of java. "));
		list.add(new Course(2,"Spring boot course","Creating rest Api"));
		
	}


	@Override
	public List<Course> getCourses() {
		return list;
	}
	 
	public Course getCourse(long courseId)
	{
		Course c = null;
		for(Course course:list)
		{
			if(course.getId()==courseId)
			{
				c=course;
				break;
			}
		}
		return c;
	}


	@Override
	public Course addCourse(Course course) {
		list.add(course);
		return course;
	}


//	@Override
//	public void deleteCourse(long courseId) {
//		CourseRepository.deleteCourse(id);
//		
//	}

	
	
//	//Adding the book
//	public Book addBook(Book b)
//	{
//		Book result = bookRepository.save(b);
//		return result;
//	}
//	// Delete the Book
//	public void deleteBook(int bid)
//	{
//		bookRepository.deleteById(bid);
//	}
//	// update the book 
//	public void updateBook(Book book, int id)
//	{
//		book.setId(id);
//		bookRepository.save(book);
//	}
//	
//	

}
