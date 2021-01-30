package com.example.isabackend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DermatologistExaminationResponse {

    private Long id;
    private LocalTime startTimeExamination;
    private LocalTime endTimeExamination;
    private LocalDate dateExamination;
    private double price;
    private DermatologistResponse dermatologist;
    private Long pharmacyId;
    private String examinationStatus;
    private Long patientId;
}