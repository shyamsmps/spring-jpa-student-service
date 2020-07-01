package com.smps.springbootjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue()
    private Integer id;

    private String subjectName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject() {
        super();
    }

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="studentId")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
        if (!student.getSubjects().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            student.getSubjects().add(this);
        }
        this.student = student;
    }

    public Student getStudent() {
        return this.student;
    }
}
