package com.example.demo.services;


import com.example.demo.pojo.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MainParameters mainParameters;

    public String reshenie(String param){
        return "Answer is " + param;
    }

    public List<Student> getAllStudents(){
        return mainParameters.getStudents();
    }

    public Student addStudent(Student student){
        try {
            mainParameters.getStudents().add(student);
            logger.info("Added student {}", student);
        } catch (Exception e) {
            logger.error("Error adding student {}", student, e);
            return null;
        }
        return student;
    }
}
