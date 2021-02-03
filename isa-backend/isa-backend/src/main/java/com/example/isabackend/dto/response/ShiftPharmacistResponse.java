package com.example.isabackend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftPharmacistResponse {
    private Long id;
    private LocalTime startShift;
    private LocalTime endShift;
    private PharmacyResponse pharmacyResponse;
    private PharmacistResponse pharmacistResponse;
}
