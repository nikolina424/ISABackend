package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePharmacistExaminationRequest {
    private String dateExamination;
    private String startExamination;
    private String endExamination;
    private Long pharmacistId;
    private Long patientId;
}
