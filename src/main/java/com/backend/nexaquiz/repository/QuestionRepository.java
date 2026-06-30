package com.backend.nexaquiz.repository;

import java.util.List;

import com.backend.nexaquiz.entity.Topic;
import com.backend.nexaquiz.entity.enums.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectId(Long subjectId);

    List<Question> findByTopicId(Long topicId);

    List<Question> findBySubjectIdAndTopicId(Long subjectId, Long topicId);

    List<Question> findBySubjectIdAndActiveTrue(Long subjectId);

    List<Question> findByTopicIdAndActiveTrue(Long topicId);

    List<Question> findByDifficultyLevel(DifficultyLevel level);

    List<Question> findByActiveTrue();

    List<Question> findByTopic_IdAndActiveTrue(
            Long topicId
    );
}