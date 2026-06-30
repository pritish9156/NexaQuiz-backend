package com.backend.nexaquiz.dto.request;

import lombok.Data;

@Data
public class TrainerRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}