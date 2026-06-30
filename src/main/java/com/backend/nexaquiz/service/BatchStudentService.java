package com.backend.nexaquiz.service;

import java.util.List;

import com.backend.nexaquiz.dto.request.AssignStudentsRequest;
import com.backend.nexaquiz.dto.response.BatchStudentResponse;

public interface BatchStudentService {

    void assignStudentsToBatch(
            AssignStudentsRequest request);

    List<BatchStudentResponse>
    getStudentsByBatch(
            Long batchId);

}