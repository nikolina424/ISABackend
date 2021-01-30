package com.example.isabackend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvailableExaminationRequest {
    private String startTimeExamination;
    private String endTimeExamination;
    private String dateExamination;
    private Long pharmacyId;
    private Long dermatologistId;
    private double price;
}