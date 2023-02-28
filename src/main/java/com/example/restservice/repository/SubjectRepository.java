package com.example.restservice.repository;


import com.example.restservice.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByTeacherId(Long id);
}