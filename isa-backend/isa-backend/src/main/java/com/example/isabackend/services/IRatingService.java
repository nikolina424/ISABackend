package com.example.isabackend.services;

import com.example.isabackend.dto.request.RateRequest;
import com.example.isabackend.dto.response.RatingResponse;

public interface IRatingService {
    RatingResponse ratePharmacy(RateRequest rateRequest);

    RatingResponse rateMedicament(RateRequest rateRequest);

    RatingResponse ratePharmacist(RateRequest rateRequest);

    RatingResponse rateDermatologist(RateRequest rateRequest);
}
