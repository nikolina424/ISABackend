package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreatePricelistRequest;
import com.example.isabackend.dto.response.PricelistResponse;

import java.util.List;

public interface IPricelistService {
    List<PricelistResponse> getAllByPharmacyId(Long id);

    PricelistResponse getActivePricelistByPharmacyId(Long id);

    PricelistResponse createPricelist(CreatePricelistRequest request);
}
