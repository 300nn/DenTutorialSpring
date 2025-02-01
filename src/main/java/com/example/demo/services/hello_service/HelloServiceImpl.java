package com.example.demo.services.hello_service;


import com.example.demo.pojo.Student;
import com.example.demo.services.MainParameters;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelloServiceImpl implements HelloService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MainParameters mainParameters;

    @Override
    public String reshenie(String param) {
        return "Answer is " + param;
    }

    @Override
    public List<Student> getAllStudents() {
        return mainParameters.getStudents();
    }

    @Override
    public Student addStudent(Student student) {
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
