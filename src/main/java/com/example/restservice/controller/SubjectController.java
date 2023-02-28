package com.example.restservice.controller;
import com.example.restservice.model.Subject;
import com.example.restservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    @Autowired
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subjectObj) {
        return subjectService.createSubject(subjectObj);
    }

    @PutMapping
    public Subject updateSubject(@RequestBody Subject subjectObj) {
        return subjectService.updateSubject(subjectObj);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{name}")
    public Subject getSubjectByName(@PathVariable String name) {
        return subjectService.getSubjectByName(name);
    }
}
