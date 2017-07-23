package edu.school.exceptionAndHandler;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7519326656060749551L;

	private long stu_id;
	
	public StudentNotFoundException(long stu_id) {
		this.stu_id = stu_id;
	}
	
	public long getStuId() {
		return stu_id;
	}
}
