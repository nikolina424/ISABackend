package com.example.isabackend.dto.request;

import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddMedicamentToPharmacyRequest {
    private Long pharmacyId;
    private Long medicamentId;
    private int quantity;
}
