package com.example.isabackend.dto.response;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentReservationResponse {

    private Long id;
    private LocalDate dateToPick;
    private PharmacyMedicamentResponse pharmacyMedicament;
    private Long patientId;
    private String medicamentReservationStatus;
}
