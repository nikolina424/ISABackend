package com.example.isabackend.services;

import com.example.isabackend.dto.request.ChangeOfferRequest;
import com.example.isabackend.dto.request.CreateOfferRequest;
import com.example.isabackend.dto.response.OfferResponse;

import java.util.List;

public interface IOfferService {
    List<OfferResponse> getAllOffersBySupplierId(Long id);

    OfferResponse createOffer(CreateOfferRequest request);

    void changeOffer(ChangeOfferRequest request);

    OfferResponse getOffer(Long id);

    List<OfferResponse> getAllOffersByPurchaseOrderId(Long id);
}
