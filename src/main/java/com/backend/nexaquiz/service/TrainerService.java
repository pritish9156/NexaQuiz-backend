package com.backend.nexaquiz.service;

import com.backend.nexaquiz.dto.request.TrainerRequest;
import com.backend.nexaquiz.dto.response.TrainerResponse;

import java.util.List;

public interface TrainerService {

    List<TrainerResponse> getAllTrainers();

    TrainerResponse createTrainer(
            TrainerRequest request
    );

    TrainerResponse updateTrainer(
            Long id,
            TrainerRequest request
    );

    void deleteTrainer(
            Long id
    );

}