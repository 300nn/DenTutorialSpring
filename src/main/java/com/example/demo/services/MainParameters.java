package com.example.demo.services;

import com.example.demo.pojo.Student;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class MainParameters {
    private final List<Student> students = new ArrayList<>();
}
