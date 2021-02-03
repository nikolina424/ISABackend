package com.example.isabackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceMedicamentResponse {

    private Long id;
    private int price;
    private PharmacyMedicamentResponse pharmacyMedicamentResponse;
    private PricelistResponse pricelistResponse;
}
