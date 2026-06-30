package com.backend.nexaquiz.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.backend.nexaquiz.dto.request.TopicRequest;
import com.backend.nexaquiz.dto.response.TopicResponse;
import com.backend.nexaquiz.service.TopicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(
            @Valid @RequestBody TopicRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicService.createTopic(request));
    }

    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics() {

        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopicById(
            @PathVariable Long id) {

        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<TopicResponse>> getTopicsBySubject(
            @PathVariable Long subjectId) {

        return ResponseEntity.ok(
                topicService.getTopicsBySubject(subjectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponse> updateTopic(
            @PathVariable Long id,
            @RequestBody TopicRequest request) {

        return ResponseEntity.ok(
                topicService.updateTopic(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(
            @PathVariable Long id) {

        topicService.deleteTopic(id);

        return ResponseEntity.noContent().build();
    }
}