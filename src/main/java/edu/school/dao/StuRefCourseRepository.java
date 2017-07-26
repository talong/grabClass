package edu.school.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.school.domain.StuRefCourse;


public interface StuRefCourseRepository extends CrudRepository<StuRefCourse, String>{

}
