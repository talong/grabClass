package edu.school.exceptionAndHandler;

public class DuplicateException extends RuntimeException {

	private static final long serialVersionUID = 7519326656060749551L;

	private long stu_id;
	
	private long course_id;
	
	public DuplicateException(long stu_id, long course_id) {
		this.stu_id = stu_id;
		this.course_id = course_id;
	}
	
	public long getCourseId() {
		return course_id;
	}
	
	public long getStuId() {
		return stu_id;
	}
}
