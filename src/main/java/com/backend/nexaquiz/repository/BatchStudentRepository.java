package com.backend.nexaquiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.BatchStudent;

public interface BatchStudentRepository
        extends JpaRepository<BatchStudent, Long> {

    List<BatchStudent>
    findByBatch_Id(Long batchId);

    List<BatchStudent>
    findByStudent_Id(Long studentId);

    Optional<BatchStudent>
    findFirstByStudent_Id(Long studentId);

}