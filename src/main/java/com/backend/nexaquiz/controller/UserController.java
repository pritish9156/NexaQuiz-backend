package com.backend.nexaquiz.controller;

import com.backend.nexaquiz.dto.request.CreateStudentRequest;
import com.backend.nexaquiz.dto.response.UserResponse;
import com.backend.nexaquiz.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/students")
    public ResponseEntity<List<UserResponse>>
    getAllStudents() {

        return ResponseEntity.ok(
                userService.getAllStudents()
        );
    }

    @PostMapping("/students")
    public ResponseEntity<UserResponse>
    createStudent(

            @RequestBody
            CreateStudentRequest request) {

        return ResponseEntity.ok(
                userService.createStudent(
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteStudent(
            @PathVariable Long id) {

        userService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse>
    updateStudent(

            @PathVariable
            Long id,

            @RequestBody
            CreateStudentRequest request) {

        return ResponseEntity.ok(

                userService.updateStudent(
                        id,
                        request));
    }
}