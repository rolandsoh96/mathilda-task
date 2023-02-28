package com.example.restservice.controller;

import com.example.restservice.model.ReportRecord;
import com.example.restservice.model.Student;
import com.example.restservice.model.Subject;
import com.example.restservice.model.Teacher;
import com.example.restservice.service.StudentService;
import com.example.restservice.service.SubjectService;
import com.example.restservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/allRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReportRecord>> getAllRecords() {
        List<ReportRecord> records = new ArrayList<>();

        List<Teacher> teachers = teacherService.getAllTeachers();
        for (Teacher teacher : teachers) {
            List<Subject> subjects = subjectService.getSubjectsByTeacher(teacher.getId());
            List<Student> students = studentService.getStudentsByTeacher(teacher.getId());
            for (Subject subject : subjects) {
                List<Student> enrolledStudents = studentService.getStudentsBySubject(subject.getId());
                ReportRecord reportRecord = new ReportRecord();
                reportRecord.setValuesForTeacher(teacher.getEmail(), subject.getName(), enrolledStudents);
            }
            for (Student student : students) {
                List<Subject> enrolledSubjects = subjectService.getSubjectsByStudent(student.getId());
                ReportRecord reportRecord = new ReportRecord();
                reportRecord.setValuesForStudent(student.getEmail(), teacher.getEmail(), enrolledSubjects);
            }
        }

        return ResponseEntity.ok(records);
    }
}

