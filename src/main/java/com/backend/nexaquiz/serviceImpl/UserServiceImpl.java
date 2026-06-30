package com.backend.nexaquiz.serviceImpl;

import com.backend.nexaquiz.dto.request.CreateStudentRequest;
import com.backend.nexaquiz.dto.response.UserResponse;
import com.backend.nexaquiz.entity.User;
import com.backend.nexaquiz.entity.enums.Role;
import com.backend.nexaquiz.repository.UserRepository;
import com.backend.nexaquiz.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllStudents() {

        return userRepository
                .findByRole(Role.STUDENT)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .build();
    }

    @Override
    public UserResponse createStudent(
            CreateStudentRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.STUDENT)
                .enabled(true)
                .build();

        User savedUser =
                userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public void deleteStudent(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updateStudent(
            Long id,
            CreateStudentRequest request) {

        User user =
                userRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Student Not Found"));

        user.setFirstName(
                request.getFirstName());

        user.setLastName(
                request.getLastName());

        user.setEmail(
                request.getEmail());

        User updatedUser =
                userRepository.save(user);

        return mapToResponse(updatedUser);
    }
}