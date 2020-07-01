package com.smps.springbootjpa.service;

import com.smps.springbootjpa.model.Student;
import com.smps.springbootjpa.repository.StudentRepository;
import com.smps.springbootjpa.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        for (Subject subject: student.getSubjects()) {
            subject.setStudent(student);
        }
        return studentRepository.save(student);
    }
}
