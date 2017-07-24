package edu.school.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.school.dao.StudentDao;
import edu.school.domain.Student;

@Controller
@RequestMapping({"/ref"})
public class StuRefCourseController {

	@Autowired
	StudentDao StudentDao;
	
	
	@RequestMapping(value="getStu", method=RequestMethod.GET)
	public @ResponseBody Student getStudentById() {
		Student stu = StudentDao.queryById(1);
		
		return stu;
	}
	
}
