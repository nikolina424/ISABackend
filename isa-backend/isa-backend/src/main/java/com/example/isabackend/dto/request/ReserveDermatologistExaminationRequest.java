package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDermatologistExaminationRequest {
    private Long reservationId;
    private Long patientId;
}
