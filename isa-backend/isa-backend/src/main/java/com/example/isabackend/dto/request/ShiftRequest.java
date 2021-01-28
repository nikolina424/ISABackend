package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRequest {
    private String startShift;
    private String endShift;
    private Long pharmacyId;
    private Long dermatologistId;
}
