package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreatePromotionRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.SubscribePatientRequest;
import com.example.isabackend.dto.response.PromotionResponse;

import java.util.List;

public interface IPromotionService {
    void subscribePatient(Long id, SubscribePatientRequest request);

    PromotionResponse createPromotion(CreatePromotionRequest request);

    List<PromotionResponse> getAllByPatientId(Long id);

    void cancelSubscription(Long id, GetIdRequest request);
}
