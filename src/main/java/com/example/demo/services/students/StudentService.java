package com.example.demo.services.students;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public boolean addStudent(Student student) {

        Student student1 = studentRepository.findStudentById(student.getId()).orElse(null);
        if (student1 != null) return false;
        studentRepository.save(student);
        return true;
    }

    @Transactional
    public boolean updateStudent(Integer id, Student student) {
        Student student1 = studentRepository.findStudentById(id).orElse(null);
        if (student1 == null) return false;
        student.setId(id);
        studentRepository.save(student);
        return true;
    }

    @Transactional
    public List<Student> getStudentsOlderThan(Integer age) {
        return studentRepository.findStudentByAgeGreaterThan(age);
    }
}
