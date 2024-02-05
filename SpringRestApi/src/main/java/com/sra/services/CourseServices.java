package com.sra.services;

import java.util.List;

import com.sra.entities.Course;

public interface CourseServices {

	public List<Course> getCourses();
	
	public Course getCourse(long courseId);
	
	public Course addCourse(Course course);
	
//	public void deleteCourse(long courseId);
	
}
