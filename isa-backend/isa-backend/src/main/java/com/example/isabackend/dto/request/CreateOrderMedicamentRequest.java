package com.example.isabackend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderMedicamentRequest {
    private int quantity;
    private Long purchaseOrderId;
    private Long medicamentId;
}
