package edu.school.dao;

import org.springframework.cache.annotation.Cacheable;

import edu.school.domain.Course;



public interface CourseDao {

	@Cacheable(value = "courseCache")
	public Course queryById(int id);

}