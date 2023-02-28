package com.example.restservice.service;

import com.example.restservice.model.Student;
import com.example.restservice.model.Subject;
import com.example.restservice.repository.StudentRepository;
import com.example.restservice.repository.SubjectRepository;
import com.example.restservice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    // Method to create a student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Method to update a student
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    // Scheduled method to update the external students
    @Scheduled(cron = "0 0 0 * * *") // run daily at midnight
    public void updateExternalStudents() {
        try {
            List<Student> studentsToSave = new ArrayList<>();

            // Fetch all students from the database in a single query
            List<Student> allStudents = getAllStudents();
            // Create a HashSet of existing emails for fast lookups
            Set<String> existingEmails = allStudents.stream()
                    .map(Student::getEmail)
                    .collect(Collectors.toSet());

            // fetch external student data from external system
            List<Student> externalStudents = fetchExternalStudents();

            for (Student externalStudent : externalStudents) {
                if (existingEmails.contains(externalStudent.getEmail())) {
                    // Update existing student
                    Student existingStudent = allStudents.stream()
                            .filter(s -> s.getEmail().equals(externalStudent.getEmail()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Cannot find existing student")); // add appropriate error handling

                    existingStudent.setExternal(true);
                    studentsToSave.add(existingStudent);
                } else {
                    // Add new external student to the batch
                    externalStudent.setExternal(true);
                    studentsToSave.add(externalStudent);
                }
            }

            // Batch save the updated and new students
            studentRepository.saveAll(studentsToSave);
        } catch (Exception e) {
            // Handle any exceptions and log error message
        }
    }

    // Method to get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to get a student by email
    public Student getStudentByEmail(String email) {
        return studentRepository.findById(email).orElse(null);
    }

    // Method to fetch external students
    private List<Student> fetchExternalStudents() {
        // Call external API to fetch student data
        // Add appropriate error handling and logging
        return new ArrayList<>();
    }

    // Method to get students by subject
    public List<Student> getStudentsBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId.toString())
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject ID: " + subjectId));
        return subject.getStudents();
    }

    // Method to get students by teacher
    public List<Student> getStudentsByTeacher(Long id) {
        List<Subject> subjects = subjectRepository.findByTeacherId(id);
        Set<Student> students = new HashSet<>();

        for (Subject subject : subjects) {
            students.addAll(subject.getStudents());
        }

        return new ArrayList<>(students);
    }
}
