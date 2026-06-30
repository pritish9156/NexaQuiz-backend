package com.backend.nexaquiz.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class AssignStudentsRequest {

    private Long batchId;

    private List<Long> studentIds;
}