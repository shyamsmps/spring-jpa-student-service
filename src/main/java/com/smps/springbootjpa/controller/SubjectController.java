package com.smps.springbootjpa.controller;

import com.smps.springbootjpa.model.Subject;
import com.smps.springbootjpa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {

        this.subjectService = subjectService;
    }

    @GetMapping(value = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> getSubjects() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjects());
    }

    @GetMapping(value = "/subjects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubjectById(@PathVariable(value = "id") Integer id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if (subject.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(subject);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find any subject with id " + id);
        }
    }

}
