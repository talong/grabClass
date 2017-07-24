package edu.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.school.domain.Course;
import edu.school.mapper.CourseMapper;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
    private CourseMapper courseMapper;

	@Override
	public Course queryById(int id) {
		return courseMapper.selectByPrimaryKey(id);
	}


    


}