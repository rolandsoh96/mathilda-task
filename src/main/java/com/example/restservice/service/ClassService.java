package com.example.restservice.service;

import com.example.restservice.exception.MaxClassSizeExceededException;
import com.example.restservice.model.Class;
import com.example.restservice.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    /**
     * Creates a new Class if the class size is less than or equal to 500 students
     * @param classObj the Class object to be created
     * @return the created Class object
     * @throws MaxClassSizeExceededException if the class size exceeds 500 students
     */
    public Class createClass(Class classObj) {
        if (classObj.getStudents().size() <= 500) {
            return classRepository.save(classObj);
        } else {
            throw new MaxClassSizeExceededException("Class size cannot exceed 500 students.");
        }
    }

    /**
     * Updates an existing Class if the class size is less than or equal to 500 students
     * @param classObj the Class object to be updated
     * @return the updated Class object
     * @throws MaxClassSizeExceededException if the class size exceeds 500 students
     */
    public Class updateClass(Class classObj) {
        if (classObj.getStudents().size() <= 500) {
            return classRepository.save(classObj);
        } else {
            throw new MaxClassSizeExceededException("Class size cannot exceed 500 students.");
        }
    }

    /**
     * Retrieves all Class objects
     * @return a List of all Class objects
     */
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    /**
     * Retrieves a Class object by name
     * @param name the name of the Class object to retrieve
     * @return the Class object with the specified name, or null if not found
     */
    public Class getClassByName(String name) {
        return classRepository.findById(name).orElse(null);
    }
}


