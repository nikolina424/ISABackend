package com.example.isabackend.dto.request;
import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.entity.Pricelist;
import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePricelistRequest {
    private int price;
    private Long pharmacistId;
    private Long pricelistId;
}
