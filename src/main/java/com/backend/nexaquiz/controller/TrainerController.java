package com.backend.nexaquiz.controller;

import com.backend.nexaquiz.dto.request.TrainerRequest;
import com.backend.nexaquiz.dto.response.TrainerResponse;
import com.backend.nexaquiz.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public List<TrainerResponse> getAll(){

        return trainerService.getAllTrainers();

    }

    @PostMapping
    public TrainerResponse create(

            @RequestBody
            TrainerRequest request){

        return trainerService.createTrainer(request);

    }

    @PutMapping("/{id}")
    public TrainerResponse update(

            @PathVariable Long id,

            @RequestBody
            TrainerRequest request){

        return trainerService.updateTrainer(
                id,
                request
        );

    }

    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable Long id){

        trainerService.deleteTrainer(id);

    }

}