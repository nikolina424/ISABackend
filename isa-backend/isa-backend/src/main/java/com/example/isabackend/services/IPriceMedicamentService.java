package com.example.isabackend.services;

import com.example.isabackend.dto.request.PriceMedicamentRequest;
import com.example.isabackend.dto.response.PriceMedicamentResponse;

import java.util.List;

public interface IPriceMedicamentService {
    List<PriceMedicamentResponse> getAllByPharmacyId(Long id);

    PriceMedicamentResponse createPriceMedicament(PriceMedicamentRequest request);
}
