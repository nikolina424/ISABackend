package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreatePurchaseOrderRequest;
import com.example.isabackend.dto.response.PurchaseOrderResponse;

public interface IPurchaseOrderService {
    PurchaseOrderResponse createPurchaseOrder(CreatePurchaseOrderRequest request);
}
