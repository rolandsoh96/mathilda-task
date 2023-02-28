package com.example.restservice.controller;

import com.example.restservice.model.Class;
import com.example.restservice.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {

    @Autowired
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public Class createClass(@RequestBody Class classObj) {
        return classService.createClass(classObj);
    }

    @PutMapping
    public Class updateClass(@RequestBody Class classObj) {
        return classService.updateClass(classObj);
    }

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{name}")
    public Class getClassByName(@PathVariable String name) {
        return classService.getClassByName(name);
    }
}
