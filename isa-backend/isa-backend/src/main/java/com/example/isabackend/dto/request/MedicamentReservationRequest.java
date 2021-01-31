package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentReservationRequest {
    private String dateToPick;
    private Long pharmacyMedicamentId;
    private Long patientId;
}
