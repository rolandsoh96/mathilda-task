package com.example.restservice.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRecord {
    private String teacherEmail;
    private String subjectName;
    private List<String> studentEmails;

    public ReportRecord() { }

    public void setValuesForTeacher(String teacherEmail, String subjectName, List<Student> enrolledStudents) {
        this.teacherEmail = teacherEmail;
        this.subjectName = subjectName;
        this.studentEmails = enrolledStudents.stream().map(Student::getEmail).collect(Collectors.toList());
    }

    public void setValuesForStudent(String studentEmail, String teacherEmailParam, List<Subject> enrolledSubjects) {
        this.teacherEmail = teacherEmailParam;
        this.studentEmails = Collections.singletonList(studentEmail);
        this.subjectName = enrolledSubjects.stream().map(Subject::getName).collect(Collectors.joining(", "));
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<String> getStudentEmails() {
        return studentEmails;
    }
}
