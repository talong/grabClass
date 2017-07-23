package edu.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.school.domain.Student;
import edu.school.mapper.StudentMapper;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
    private StudentMapper studentMapper;

	@Override
	public Student queryById(int id) {
		return studentMapper.selectByPrimaryKey(id);
	}


    


}