package com.backend.nexaquiz.serviceImpl;

import java.util.List;

import com.backend.nexaquiz.repository.*;
import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.QuestionRequest;
import com.backend.nexaquiz.dto.response.QuestionResponse;
import com.backend.nexaquiz.entity.Question;
import com.backend.nexaquiz.entity.Subject;
import com.backend.nexaquiz.entity.Topic;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    @Override
    public QuestionResponse createQuestion(
            QuestionRequest request) {

        Subject subject =
                subjectRepository.findById(
                                request.getSubjectId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        Topic topic =
                topicRepository.findById(
                                request.getTopicId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Topic not found"));

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .optionC(request.getOptionC())
                .optionD(request.getOptionD())
                .correctAnswer(request.getCorrectAnswer())
                .codeSnippet(request.getCodeSnippet())
                .difficultyLevel(
                        request.getDifficultyLevel())
                .subject(subject)
                .topic(topic)
                .active(true)
                .build();

        return map(
                questionRepository.save(question));
    }

    @Override
    public QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Question not found"));

        Subject subject =
                subjectRepository.findById(
                                request.getSubjectId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        Topic topic =
                topicRepository.findById(
                                request.getTopicId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Topic not found"));

        question.setQuestionText(
                request.getQuestionText());

        question.setOptionA(
                request.getOptionA());

        question.setOptionB(
                request.getOptionB());

        question.setOptionC(
                request.getOptionC());

        question.setOptionD(
                request.getOptionD());

        question.setCorrectAnswer(
                request.getCorrectAnswer());

        question.setCodeSnippet(
                request.getCodeSnippet());

        question.setDifficultyLevel(
                request.getDifficultyLevel());

        question.setSubject(subject);

        question.setTopic(topic);

        return map(
                questionRepository.save(question));
    }

    @Override
    public void deleteQuestion(Long id) {

        Question question =
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Question not found"));

        question.setActive(false);

        questionRepository.save(question);
    }

    @Override
    public QuestionResponse getQuestionById(
            Long id) {

        return map(
                questionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Question not found")));
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {

        return questionRepository.findByActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<QuestionResponse> getQuestionsBySubject(
            Long subjectId) {

        return questionRepository
                .findBySubjectIdAndActiveTrue(subjectId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<QuestionResponse> getQuestionsByTopic(
            Long topicId) {

        return questionRepository
                .findByTopicIdAndActiveTrue(topicId)
                .stream()
                .map(this::map)
                .toList();
    }

    private QuestionResponse map(
            Question question) {

        return QuestionResponse.builder()
                .id(question.getId())
                .subjectId(
                        question.getSubject().getId()
                )

                .topicId(
                        question.getTopic().getId()
                )
                .questionText(question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .correctAnswer(question.getCorrectAnswer())
                .codeSnippet(question.getCodeSnippet())
                .difficultyLevel(question.getDifficultyLevel())
                .subjectName(question.getSubject().getName())
                .topicName(question.getTopic().getName())
                .active(question.getActive())
                .build();
    }




}