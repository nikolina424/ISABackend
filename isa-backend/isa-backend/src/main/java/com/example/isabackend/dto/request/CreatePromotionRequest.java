package com.example.isabackend.dto.request;
import com.example.isabackend.dto.response.PharmacyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePromotionRequest {
    private String expireDate;
    private Long pharmacyId;
    private String description;
}
