package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseOrderRequest {
    private Long pharmacyId;
    private String limitDate;
    private Long pharmacistId;
}
