package edu.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.school.dao.StudentDao;

@Service   //ʵ�־����ҵ���߼�
public class StudentService {

    @Autowired
    private StudentDao studentDao;
    
    
}
