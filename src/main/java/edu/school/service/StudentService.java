package edu.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.school.dao.StudentDao;

@Service   //实现具体的业务逻辑
public class StudentService {

    @Autowired
    private StudentDao studentDao;
    
    
}
