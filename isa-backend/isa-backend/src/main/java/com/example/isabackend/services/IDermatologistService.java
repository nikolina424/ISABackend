package com.example.isabackend.services;

import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.SearchDermatologistResponse;

import java.util.List;

public interface IDermatologistService {
    List<DermatologistResponse> getAllDermatologists();

    DermatologistResponse getDermatologistById(Long id);

    List<DermatologistResponse> getAllDermatologistByPharmacyId(Long id);

    SearchDermatologistResponse searchDermatologists(String firstName, String lastName);
}
