package com.ljh;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arsenal
 * created on 2020/11/29 19:18
 */
@RestController
@SpringBootApplication
public class StudentServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApp.class, args);
    }

    /**
     * http://localhost:8090/echoStudentName/david
     */
    @RequestMapping("/echoStudentName/{name}")
    public String echoStudentName(@PathVariable("name") String name) {
        return "Hello " + name + "! Nice to meet you !";
    }

    /**
     * http://localhost:8090/getStudentDetail/david
     */
    @RequestMapping("/getStudentDetail/{name}")
    public Student getStudentDetails(@PathVariable("name") String name) {
        return new Student(name, "sh", "www");
    }

    @Data
    @AllArgsConstructor
    private static class Student {
        private String name;
        private String address;
        private String cls;
    }
}
