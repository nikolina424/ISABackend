package com.example.isabackend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyMedicamentResponse {
    private Long id;
    private int quantity;
    private MedicamentResponse medicament;
    private PharmacyResponse pharmacy;
}
