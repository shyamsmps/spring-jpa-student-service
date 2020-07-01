package com.smps.springbootjpa.controller;

import com.smps.springbootjpa.model.Student;
import com.smps.springbootjpa.model.Subject;
import com.smps.springbootjpa.service.StudentService;
import com.smps.springbootjpa.service.SubjectService;
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
    SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, SubjectService subjectService) {

        this.studentService = studentService;
        this.subjectService = subjectService;
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

    @GetMapping(value = "/students/{id}/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubjectsByStudentId(@PathVariable(value = "id") Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectsByStudent(student.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find any student with id " + id);
        }
    }

    @PostMapping(value="/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @PostMapping(value="/students/{id}/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSubjects(@PathVariable(value = "id") Integer id, @RequestBody List<Subject> subjects) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            for(Subject subject: subjects) {
                subject.setStudent(student.get());
            }
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.createSubjects(subjects));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find any student with id " + id);
        }
    }
}
