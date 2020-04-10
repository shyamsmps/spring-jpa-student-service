package com.smps.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }

    @GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStudentById(@PathVariable(value = "id") Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find any student with id " + id);
        }
    }

    @PostMapping(value="/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }
}
