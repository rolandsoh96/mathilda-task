package com.example.restservice.service;

import com.example.restservice.model.Teacher;
import com.example.restservice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Creates a new teacher in the system.
     *
     * @param teacher the teacher object to be created.
     * @return the created teacher object.
     */
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * Updates an existing teacher in the system.
     *
     * @param teacher the teacher object to be updated.
     * @return the updated teacher object.
     */
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * Retrieves a list of all teachers in the system.
     *
     * @return a list of all teachers.
     */
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        System.out.println(teachers.size()); // TODO: Remove this debugging line.
        return teacherRepository.findAll();
    }

    /**
     * Retrieves a teacher by email address.
     *
     * @param email the email address of the teacher.
     * @return the teacher object with the specified email address, or null if not found.
     */
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findById(email).orElse(null);
    }
}
