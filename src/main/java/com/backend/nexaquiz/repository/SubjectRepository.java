package com.backend.nexaquiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByName(String name);

    boolean existsByName(String name);

    List<Subject> findByActiveTrue();

    long countByActiveTrue();
}