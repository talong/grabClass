package edu.school.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.school.domain.StuRefCourse;
import edu.school.exceptionAndHandler.StudentNotFoundException;
import edu.school.modle.MyError;
import edu.school.service.StuRefCourseService;

@Controller
@RequestMapping({"/refRest"})
public class StuRefCourseRestfulController {

	@Autowired
	StuRefCourseService stuRefCourseService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody StuRefCourse createRef(@RequestParam(value = "stu_id") int stu_id,
			@RequestParam(value = "course_id") int course_id) {
		stuRefCourseService.grabCourse(stu_id, course_id);
		StuRefCourse src = new StuRefCourse();
		src.setCourse_id(course_id);
		src.setStu_id(stu_id);
		return src;
	}
	
	
	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody MyError studentNotFound(
			StudentNotFoundException e) {
		long studentId = e.getStuId();
		return new MyError(4, "Student[" + studentId + "] not found");
	}
}
