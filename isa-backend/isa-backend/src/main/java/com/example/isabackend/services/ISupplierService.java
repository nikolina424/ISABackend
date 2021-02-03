package com.example.isabackend.services;

import com.example.isabackend.dto.request.UpdateSupplierRequest;
import com.example.isabackend.dto.response.SupplierResponse;

public interface ISupplierService {
    void updateSupplier(Long id, UpdateSupplierRequest request);

    SupplierResponse getSupplier(Long id);
}
