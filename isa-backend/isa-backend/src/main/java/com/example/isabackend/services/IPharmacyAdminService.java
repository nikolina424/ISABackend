package com.example.isabackend.services;

import com.example.isabackend.dto.request.UpdatePharmacyAdminRequest;
import com.example.isabackend.dto.response.PharmacyAdminResponse;

public interface IPharmacyAdminService {
    PharmacyAdminResponse getPharmacyAdminById(Long id);

    PharmacyAdminResponse getPharmacyAdminByUserId(Long id);

    void updatePharmacyAdmin(Long id, UpdatePharmacyAdminRequest request);
}
