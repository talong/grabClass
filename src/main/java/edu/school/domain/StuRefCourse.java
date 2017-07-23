package edu.school.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class StuRefCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	@NotNull
	private Long stu_id;

	@NotNull
	private Long course_id;
	  
	  
	public StuRefCourse() {}
	  
	public StuRefCourse(Long stu_id, Long course_id) {
	    this(null, stu_id, course_id);
	}

	public StuRefCourse(Long id, Long stu_id, Long course_id) {
	    this.id = id;
	    this.stu_id = stu_id;
	    this.course_id = course_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStu_id() {
		return stu_id;
	}

	public void setStu_id(Long stu_id) {
		this.stu_id = stu_id;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	

}
