package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.TopicRequest;
import com.backend.nexaquiz.dto.response.TopicResponse;

public interface TopicService {

    TopicResponse createTopic(TopicRequest request);

    TopicResponse updateTopic(Long id, TopicRequest request);

    void deleteTopic(Long id);

    TopicResponse getTopicById(Long id);

    List<TopicResponse> getAllTopics();

    List<TopicResponse> getTopicsBySubject(Long subjectId);
}