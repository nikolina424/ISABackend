package com.example.isabackend.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOfferRequest {
    private double price;
    private String deliveryDate;
    private Long purchaseOrderId;
    private Long supplierId;
}
