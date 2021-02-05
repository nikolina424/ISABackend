package com.example.isabackend.dto.response;
import com.example.isabackend.entity.Pharmacy;
import com.example.isabackend.util.enums.PurchaseOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderResponse {
    private Long id;
    private PharmacyResponse pharmacy;
    private LocalDate limitDate;
    private PharmacistResponse pharmacist;
    private boolean active;
    private PurchaseOrderStatus purchaseOrderStatus;
}
