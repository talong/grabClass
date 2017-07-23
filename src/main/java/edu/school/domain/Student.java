package edu.school.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	  
	@NotNull
	private String name;

	@NotNull
	private String student_no;
	  
	@NotNull
	private int number;
	  
	  
	public Student() {}
	  
	public Student(String name, String student_no, int number) {
	    this(null, name, student_no, number);
	}

	public Student(Integer id, String name, String student_no, int number) {
	    this.id = id;
	    this.name = name;
	    this.student_no = student_no;
	    this.number = number;
	}
	  

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	  
	@Override
	public boolean equals(Object that) {
	    return EqualsBuilder.reflectionEquals(this, that, "name", "student_no");
	}
	  
	@Override
	public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this, "name", "student_no");
	} 
	
	
}
