package com.backend.nexaquiz.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.TopicRequest;
import com.backend.nexaquiz.dto.response.TopicResponse;
import com.backend.nexaquiz.entity.Subject;
import com.backend.nexaquiz.entity.Topic;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.SubjectRepository;
import com.backend.nexaquiz.repository.TopicRepository;
import com.backend.nexaquiz.service.TopicService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public TopicResponse createTopic(TopicRequest request) {

        Subject subject = subjectRepository
                .findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found"));

        Topic topic = Topic.builder()
                .name(request.getName())
                .description(request.getDescription())
                .subject(subject)
                .active(true)
                .build();

        return map(topicRepository.save(topic));
    }

    @Override
    public TopicResponse updateTopic(Long id,
                                     TopicRequest request) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found"));

        Subject subject = subjectRepository
                .findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found"));

        topic.setName(request.getName());
        topic.setDescription(request.getDescription());
        topic.setSubject(subject);

        return map(topicRepository.save(topic));
    }

    @Override
    public void deleteTopic(Long id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found"));

        topic.setActive(false);

        topicRepository.save(topic);
    }

    @Override
    public TopicResponse getTopicById(Long id) {

        return map(topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic not found")));
    }

    @Override
    public List<TopicResponse> getAllTopics() {

        return topicRepository.findByActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<TopicResponse> getTopicsBySubject(Long subjectId) {

        return topicRepository
                .findBySubjectIdAndActiveTrue(subjectId)
                .stream()
                .map(this::map)
                .toList();
    }

    private TopicResponse map(Topic topic) {

        return TopicResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .subjectId(topic.getSubject().getId())
                .subjectName(topic.getSubject().getName())
                .active(topic.getActive())
                .build();
    }
}