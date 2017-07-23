package edu.school.dao;

import edu.school.domain.StuRefCourse;


public interface StuRefCourseDao {

	public void insert(StuRefCourse stuRefCourse);
	
	public int getGrabCourseNum(int stu_id, int course_id);
	
	public int getCourseNum(int course_id);

	public int getCountTime(int stu_id, String time_for_class);

}