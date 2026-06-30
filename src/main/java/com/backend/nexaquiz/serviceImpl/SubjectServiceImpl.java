package com.backend.nexaquiz.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.SubjectRequest;
import com.backend.nexaquiz.dto.response.SubjectResponse;
import com.backend.nexaquiz.entity.Subject;
import com.backend.nexaquiz.exception.DuplicateResourceException;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.SubjectRepository;
import com.backend.nexaquiz.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectResponse createSubject(
            SubjectRequest request) {

        if(subjectRepository.existsByName(
                request.getName())) {

            throw new DuplicateResourceException(
                    "Subject already exists");
        }

        Subject subject = Subject.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();

        subject = subjectRepository.save(subject);

        return mapToResponse(subject);
    }

    @Override
    public SubjectResponse updateSubject(
            Long id,
            SubjectRequest request) {

        Subject subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        subject.setName(request.getName());
        subject.setDescription(
                request.getDescription());

        subject = subjectRepository.save(subject);

        return mapToResponse(subject);
    }

    @Override
    public void deleteSubject(Long id) {

        Subject subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        subject.setActive(false);

        subjectRepository.save(subject);
    }

    @Override
    public SubjectResponse getSubjectById(
            Long id) {

        Subject subject =
                subjectRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        return mapToResponse(subject);
    }

    @Override
    public List<SubjectResponse>
    getAllSubjects() {

        return subjectRepository
                .findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SubjectResponse mapToResponse(
            Subject subject) {

        return SubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .active(subject.getActive())
                .build();
    }
}