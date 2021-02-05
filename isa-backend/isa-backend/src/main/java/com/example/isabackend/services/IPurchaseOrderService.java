package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreatePurchaseOrderRequest;
import com.example.isabackend.dto.response.PurchaseOrderResponse;

import java.util.List;

public interface IPurchaseOrderService {
    PurchaseOrderResponse createPurchaseOrder(CreatePurchaseOrderRequest request);

    List<PurchaseOrderResponse> getAllByPharmacyId(Long id);

    List<PurchaseOrderResponse> getAllByActiveStatus();
}
