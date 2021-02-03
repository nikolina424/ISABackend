package com.example.isabackend.dto.response;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricelistResponse {

    private Long id;
    private boolean active;
    private LocalDate fromDate;
    private LocalDate toDate;
    private PharmacyResponse pharmacyResponse;


}
