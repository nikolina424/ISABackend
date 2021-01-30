package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialShiftRequest {
    private Long DermatologistId;
    private Long PharmacyId;
}
