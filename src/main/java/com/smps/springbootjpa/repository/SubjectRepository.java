package com.smps.springbootjpa.repository;

import com.smps.springbootjpa.model.Student;
import com.smps.springbootjpa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    // derived query method
    List<Subject> findByStudent(Student student);
}
