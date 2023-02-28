package com.example.restservice.service;

import com.example.restservice.model.Student;
import com.example.restservice.model.Subject;
import com.example.restservice.model.Teacher;
import com.example.restservice.repository.StudentRepository;
import com.example.restservice.repository.SubjectRepository;
import com.example.restservice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Creates a new subject in the database.
     *
     * @param subjectObj the subject to be created.
     * @return the created subject.
     */
    public Subject createSubject(Subject subjectObj) {
        return subjectRepository.save(subjectObj);
    }

    /**
     * Updates an existing subject in the database.
     *
     * @param subjectObj the subject to be updated.
     * @return the updated subject.
     */
    public Subject updateSubject(Subject subjectObj) {
        return subjectRepository.save(subjectObj);
    }

    /**
     * Retrieves all subjects in the database.
     *
     * @return a list of all subjects.
     */
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    /**
     * Retrieves a subject by its name.
     *
     * @param name the name of the subject to be retrieved.
     * @return the subject with the given name, or null if not found.
     */
    public Subject getSubjectByName(String name) {
        return subjectRepository.findById(name).orElse(null);
    }

    /**
     * Retrieves all subjects taught by a given teacher.
     *
     * @param teacherId the ID of the teacher whose subjects to retrieve.
     * @return a list of all subjects taught by the given teacher.
     * @throws IllegalArgumentException if the given teacher ID is invalid.
     */
    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId.toString())
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher ID: " + teacherId));
        return teacher.getSubjects();
    }

    /**
     * Retrieves all subjects taken by a given student.
     *
     * @param id the ID of the student whose subjects to retrieve.
     * @return a list of all subjects taken by the given student.
     * @throws IllegalArgumentException if the given student ID is invalid.
     */
    public List<Subject> getSubjectsByStudent(Long id) {
        Student student = studentRepository.findById(id.toString())
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        return student.getSubjects();
    }

}
