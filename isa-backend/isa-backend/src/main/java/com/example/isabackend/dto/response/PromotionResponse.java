package com.example.isabackend.dto.response;
import com.example.isabackend.entity.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponse {
    private Long id;
    private boolean active;
    private LocalDate expireDate;
    private PharmacyResponse pharmacyResponse;
    private String description;
}
