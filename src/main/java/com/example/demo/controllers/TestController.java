package com.example.demo.controllers;

import com.example.demo.pojo.Student;
import com.example.demo.services.hello_service.HelloService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HelloService helloServiceImpl;

    @GetMapping("/tastk1")
    public String getAnswer(String a){
        logger.info("Answer is {}", a);
        return helloServiceImpl.reshenie(a);
    }

    @GetMapping("/student")
    public List<Student> hello() {
        return helloServiceImpl.getAllStudents();
    }

    @PostMapping("/student")
    public Student helloPost(@RequestParam("per") String per, @RequestBody Student student) {
        logger.info("per: {}", per);
        return helloServiceImpl.addStudent(student);
    }
}
