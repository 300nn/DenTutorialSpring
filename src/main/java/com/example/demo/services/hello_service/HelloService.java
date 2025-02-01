package com.example.demo.services.hello_service;

import com.example.demo.pojo.Student;

import java.util.List;

public interface HelloService {
    String reshenie(String param);
    List<Student> getAllStudents();
    Student addStudent(Student student);
}
