package com.example.restservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "subject_class_mapping",
            joinColumns = { @JoinColumn(name = "subject_name") },
            inverseJoinColumns = { @JoinColumn(name = "class_name") }
    )
    private List<Class> classes;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    public Subject() { }

    public Subject(Long id, String name, List<Class> classes, List<Student> students, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.students = students;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}