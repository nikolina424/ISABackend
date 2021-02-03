package com.example.isabackend.services;

import com.example.isabackend.dto.request.PharmacistRequest;
import com.example.isabackend.dto.request.UpdatePharmacistRequest;
import com.example.isabackend.dto.response.PharmacistResponse;
import com.example.isabackend.dto.response.SearchPharmacistResponse;

import java.util.List;

public interface IPharmacistService {
    SearchPharmacistResponse searchPharmacist(String firstName, String lastName);

    List<PharmacistResponse> getAllPharmacistByPharmacyId(Long id);

    void updatePharmacist(Long id, UpdatePharmacistRequest request);

    PharmacistResponse getPharmacist(Long id);
}
