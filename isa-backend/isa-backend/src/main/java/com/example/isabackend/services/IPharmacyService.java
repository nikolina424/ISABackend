package com.example.isabackend.services;

import com.example.isabackend.dto.request.UpdatePharmacyRequest;
import com.example.isabackend.dto.response.PharmacyResponse;

import java.util.List;

public interface IPharmacyService {
    void updatePharmacy(Long id, UpdatePharmacyRequest request);

    PharmacyResponse getPharmacyById(Long id);

    List<PharmacyResponse> getAllPharmacies();
}
