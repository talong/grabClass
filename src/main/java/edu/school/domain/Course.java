package edu.school.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	  
	@NotNull
	private String name;

	@NotNull
	private String time_for_class;
	  
	@NotNull
	private int number;
	  
	  
	public Course() {}
	  
/*	public Course(String name, String time_for_class, int number) {
	    this(null, name, time_for_class, number);
	}

	public Course(Integer id, String name, String time_for_class, int number) {
	    this.id = id;
	    this.name = name;
	    this.time_for_class = time_for_class;
	    this.number = number;
	}*/
	  
	

	  
	public int getId() {
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

	public String getTime_for_class() {
		return time_for_class;
	}

	public void setTime_for_class(String time_for_class) {
		this.time_for_class = time_for_class;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object that) {
	    return EqualsBuilder.reflectionEquals(this, that, "name", "time_for_class");
	}
	  
	@Override
	public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this, "name", "time_for_class");
	} 
	
}
