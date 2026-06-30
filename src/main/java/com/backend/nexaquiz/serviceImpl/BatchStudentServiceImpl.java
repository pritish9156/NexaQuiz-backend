package com.backend.nexaquiz.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.AssignStudentsRequest;
import com.backend.nexaquiz.dto.response.BatchStudentResponse;
import com.backend.nexaquiz.entity.Batch;
import com.backend.nexaquiz.entity.BatchStudent;
import com.backend.nexaquiz.entity.User;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.BatchRepository;
import com.backend.nexaquiz.repository.BatchStudentRepository;
import com.backend.nexaquiz.repository.UserRepository;
import com.backend.nexaquiz.service.BatchStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchStudentServiceImpl
        implements BatchStudentService {

    private final BatchRepository batchRepository;

    private final UserRepository userRepository;

    private final BatchStudentRepository
            batchStudentRepository;

    @Override
    public void assignStudentsToBatch(
            AssignStudentsRequest request) {

        Batch batch =
                batchRepository.findById(
                                request.getBatchId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Batch Not Found"));

        batchStudentRepository.deleteAll(
                batchStudentRepository
                        .findByBatch_Id(
                                batch.getId()));

        for(Long studentId :
                request.getStudentIds()) {

            if(studentId == null){
                continue;
            }

            User student =
                    userRepository.findById(
                                    studentId)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Student Not Found"));

            BatchStudent batchStudent =
                    BatchStudent.builder()
                            .batch(batch)
                            .student(student)
                            .build();

            batchStudentRepository.save(
                    batchStudent);
        }
    }

    @Override
    public List<BatchStudentResponse>
    getStudentsByBatch(
            Long batchId) {

        return batchStudentRepository
                .findByBatch_Id(batchId)
                .stream()
                .map(bs ->

                        BatchStudentResponse
                                .builder()
                                .studentId(
                                        bs.getStudent()
                                                .getId())
                                .firstName(
                                        bs.getStudent()
                                                .getFirstName())
                                .lastName(
                                        bs.getStudent()
                                                .getLastName())
                                .email(
                                        bs.getStudent()
                                                .getEmail())
                                .build()

                )
                .toList();
    }
}