package edu.school.dao;

import org.springframework.cache.annotation.CachePut;

import edu.school.domain.StuRefCourse;


public interface StuRefCourseDao {

	@CachePut(value = "stuRefCourse", key = "#result.id")
	public int insert(StuRefCourse stuRefCourse);
	
	public int getGrabCourseNum(int stu_id, int course_id);
	
	public int getCourseNum(int course_id);

	public int getCountTime(int stu_id, String time_for_class);

	public int getGrabCourseNum(int stu_id);

	public StuRefCourse getStuRefCourse(StuRefCourse stuRefCourse);


}