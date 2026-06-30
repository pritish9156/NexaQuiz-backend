package com.backend.nexaquiz.serviceImpl;

import com.backend.nexaquiz.dto.request.BatchRequest;
import com.backend.nexaquiz.dto.response.BatchResponse;
import com.backend.nexaquiz.entity.Batch;
import com.backend.nexaquiz.repository.BatchRepository;
import com.backend.nexaquiz.service.BatchService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl
        implements BatchService {

    private final BatchRepository batchRepository;

    @Override
    public BatchResponse createBatch(
            BatchRequest request) {

        Batch batch =
                Batch.builder()
                        .name(request.getName())
                        .description(
                                request.getDescription())
                        .build();

        return map(
                batchRepository.save(batch));
    }

    @Override
    public List<BatchResponse>
    getAllBatches() {

        return batchRepository
                .findByActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public BatchResponse getBatchById(
            Long id) {

        return map(

                batchRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Batch Not Found"))

        );
    }

    @Override
    public BatchResponse updateBatch(
            Long id,
            BatchRequest request) {

        Batch batch =
                batchRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Batch Not Found"));

        batch.setName(
                request.getName());

        batch.setDescription(
                request.getDescription());

        return map(
                batchRepository.save(batch));
    }

    @Override
    public void deleteBatch(
            Long id) {

        Batch batch =
                batchRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Batch Not Found"));

        batch.setActive(false);

        batchRepository.save(batch);
    }

    private BatchResponse map(
            Batch batch) {

        return BatchResponse.builder()
                .id(batch.getId())
                .name(batch.getName())
                .description(
                        batch.getDescription())
                .active(batch.getActive())
                .build();
    }
}