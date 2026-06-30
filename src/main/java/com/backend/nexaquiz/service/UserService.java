package com.backend.nexaquiz.service;

import com.backend.nexaquiz.dto.request.CreateStudentRequest;
import com.backend.nexaquiz.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllStudents();

    UserResponse createStudent(
            CreateStudentRequest request);

    void deleteStudent(Long id);

    UserResponse updateStudent(
            Long id,
            CreateStudentRequest request);

}