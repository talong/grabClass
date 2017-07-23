package edu.school.exceptionAndHandler;

public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7519326656060749551L;

	private long course_id;
	
	public CourseNotFoundException(long course_id) {
		this.course_id = course_id;
	}
	
	public long getCourseId() {
		return course_id;
	}
}
