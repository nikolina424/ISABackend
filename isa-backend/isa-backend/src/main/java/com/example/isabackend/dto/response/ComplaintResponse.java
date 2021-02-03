package com.example.isabackend.dto.response;

import com.example.isabackend.entity.Dermatologist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintResponse {
    private Long id;
    private PatientResponse patientResponse;
    private String text;
    private boolean answered;
    private PharmacistResponse pharmacistResponse;
    private DermatologistResponse dermatologistResponse;
}
