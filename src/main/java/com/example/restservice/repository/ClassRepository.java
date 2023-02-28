package com.example.restservice.repository;

import com.example.restservice.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {
}
