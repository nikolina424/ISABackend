package com.example.isabackend.dto.response;
import com.example.isabackend.util.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponse {
    private Long id;
    private double price;
    private LocalDate deliveryDate;
    private PurchaseOrderResponse purchaseOrder;
    private SupplierResponse supplier;
    private OfferStatus offerStatus;
}
