package com.backend.nexaquiz.repository;

import java.util.List;

import com.backend.nexaquiz.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findBySubjectId(Long subjectId);

    List<Topic> findBySubjectIdAndActiveTrue(Long subjectId);

    List<Topic> findByActiveTrue();

    long countByActiveTrue();
}