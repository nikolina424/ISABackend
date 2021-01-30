package com.example.isabackend.dto.response;


import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftResponse {
    private Long id;
    private LocalTime startShift;
    private LocalTime endShift;
    private Long dermatologistId;
    private Long pharmacyId;
}
