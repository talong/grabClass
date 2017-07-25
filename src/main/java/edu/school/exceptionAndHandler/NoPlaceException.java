package edu.school.exceptionAndHandler;

public class NoPlaceException extends RuntimeException {

	private static final long serialVersionUID = 7519326656060749551L;

	private long course_id;
	
	public NoPlaceException(long course_id) {
		this.course_id = course_id;
	}
	
	public long getCourseId() {
		return course_id;
	}
}
