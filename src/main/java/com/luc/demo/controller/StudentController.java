package com.luc.demo.controller;

import com.luc.demo.model.Student;
import com.luc.demo.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentServices.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        studentServices.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentServices.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) String lastName) {
        studentServices.updateStudent(studentId, name, lastName);

    }
}
