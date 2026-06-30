package com.backend.nexaquiz.serviceImpl;

import com.backend.nexaquiz.dto.request.TrainerRequest;
import com.backend.nexaquiz.dto.response.TrainerResponse;
import com.backend.nexaquiz.entity.User;
import com.backend.nexaquiz.entity.enums.Role;
import com.backend.nexaquiz.repository.UserRepository;
import com.backend.nexaquiz.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl
        implements TrainerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private TrainerResponse map(User user){

        return TrainerResponse.builder()

                .id(user.getId())

                .firstName(user.getFirstName())

                .lastName(user.getLastName())

                .email(user.getEmail())

                .enabled(user.getEnabled())

                .build();

    }

    @Override
    public List<TrainerResponse> getAllTrainers() {

        return userRepository

                .findByRoleOrderByFirstName(Role.TRAINER)

                .stream()

                .map(this::map)

                .toList();

    }

    @Override
    public TrainerResponse createTrainer(
            TrainerRequest request) {

        User trainer = User.builder()

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .role(Role.TRAINER)

                .enabled(true)

                .build();

        return map(

                userRepository.save(trainer)

        );

    }

    @Override
    public TrainerResponse updateTrainer(
            Long id,
            TrainerRequest request) {

        User trainer = userRepository

                .findById(id)

                .orElseThrow();

        trainer.setFirstName(
                request.getFirstName());

        trainer.setLastName(
                request.getLastName());

        trainer.setEmail(
                request.getEmail());

        if(request.getPassword()!=null
                &&
                !request.getPassword().isBlank()){

            trainer.setPassword(

                    passwordEncoder.encode(
                            request.getPassword()
                    )

            );

        }

        return map(

                userRepository.save(trainer)

        );

    }

    @Override
    public void deleteTrainer(Long id) {

        userRepository.deleteById(id);

    }

}