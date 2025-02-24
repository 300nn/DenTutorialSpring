package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @NonNull
    List<Student> findAll();

    Optional<Student> findStudentById(Integer id);

    List<Student> findStudentByAgeGreaterThan(int age);

    void deleteStudentById(Integer id);
}
