package com.example.restservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "is_external")
    private boolean isExternal;

    @ManyToMany
    @JoinTable(
            name = "class_student_mapping",
            joinColumns = { @JoinColumn(name = "student_email") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    private List<Class> classes;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    public Student() { }

    public Student(Long id, String email, String name, boolean isExternal, List<Class> classes, List<Subject> subjects) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.isExternal = isExternal;
        this.classes = classes;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}