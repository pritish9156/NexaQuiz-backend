package com.backend.nexaquiz.service;

import com.backend.nexaquiz.dto.request.BatchRequest;
import com.backend.nexaquiz.dto.response.BatchResponse;

import java.util.List;

public interface BatchService {

    BatchResponse createBatch(
            BatchRequest request);

    List<BatchResponse> getAllBatches();

    BatchResponse getBatchById(
            Long id);

    BatchResponse updateBatch(
            Long id,
            BatchRequest request);

    void deleteBatch(
            Long id);
}