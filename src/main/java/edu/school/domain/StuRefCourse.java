package edu.school.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("stuRefCourse")
public class StuRefCourse implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	  
	@NotNull
	private Integer stu_id;

	@NotNull
	private Integer course_id;
	  
	  
	public StuRefCourse() {}
	  
	public StuRefCourse(Integer stu_id, Integer course_id) {
	    this(null, stu_id, course_id);
	}

	public StuRefCourse(Integer id, Integer stu_id, Integer course_id) {
	    this.id = id;
	    this.stu_id = stu_id;
	    this.course_id = course_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStu_id() {
		return stu_id;
	}

	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getSrcKey() {
		return "StuRefCourse" + id + stu_id + course_id;
	}
	
    public String toString(){
		return "id=" + id + ",stu_id=" + stu_id + ",course_id=" + course_id;
    	
    }
	
}
