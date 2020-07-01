package com.smps.springbootjpa.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue()
    private Integer id;

    private String name;

    private int age;

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "student")
    private Set<Subject> subjects;

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        if (subject.getStudent() != this) {
            subject.setStudent(this);
        }
    }

}
