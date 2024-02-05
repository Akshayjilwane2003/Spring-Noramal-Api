package com.sra.services.dao;

import org.springframework.data.repository.CrudRepository;

import com.sra.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
//	public Course findBfindBy(long id);
}
