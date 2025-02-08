package com.example.demo.controllers;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.students.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping("/getAllStudents")
    List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/addStudent")
    ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (studentService.addStudent(student))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable("id") Integer id) {
        return studentRepository.findStudentById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    ResponseEntity<?> deleteStudent(@PathVariable("id") Integer id) {
        studentRepository.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<?> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        if (studentService.updateStudent(id, student))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/olderThan/{age}")
    List<Student> olderThan(@PathVariable("age") Integer age) {
        return studentService.getStudentsOlderThan(age);
    }
}
