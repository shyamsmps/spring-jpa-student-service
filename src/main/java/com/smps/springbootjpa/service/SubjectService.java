package com.smps.springbootjpa.service;

import com.smps.springbootjpa.model.Student;
import com.smps.springbootjpa.model.Subject;
import com.smps.springbootjpa.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Integer id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> getSubjectsByStudent(Student student) {
        return subjectRepository.findByStudent(student);
    }

    public List<Subject> createSubjects(List<Subject> subjects) {
        return subjectRepository.saveAll(subjects);
    }

}
