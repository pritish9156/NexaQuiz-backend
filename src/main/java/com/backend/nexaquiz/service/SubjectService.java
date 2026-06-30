package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.SubjectRequest;
import com.backend.nexaquiz.dto.response.SubjectResponse;

public interface SubjectService {

    SubjectResponse createSubject(SubjectRequest request);

    SubjectResponse updateSubject(Long id,
                                  SubjectRequest request);

    void deleteSubject(Long id);

    SubjectResponse getSubjectById(Long id);

    List<SubjectResponse> getAllSubjects();
}