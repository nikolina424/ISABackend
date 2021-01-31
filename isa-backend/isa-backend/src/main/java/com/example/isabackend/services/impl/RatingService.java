package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.RateRequest;
import com.example.isabackend.dto.response.RatingResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.*;
import com.example.isabackend.services.IRatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingService implements IRatingService {

    private final IRatingRepository _ratingRepository;
    private final IPharmacyRepository _pharmacyRepository;
    private final IMedicamentRepository _medicamentRepository;
    private final IPharmacistRepository _pharmacistRepository;
    private final IDermatologistRepository _dermatologistRepository;

    public RatingService(IRatingRepository ratingRepository, IPharmacyRepository pharmacyRepository, IMedicamentRepository medicamentRepository, IPharmacistRepository pharmacistRepository, IDermatologistRepository dermatologistRepository) {
        _ratingRepository = ratingRepository;
        _pharmacyRepository = pharmacyRepository;
        _medicamentRepository = medicamentRepository;
        _pharmacistRepository = pharmacistRepository;
        _dermatologistRepository = dermatologistRepository;
    }

    @Override
    public RatingResponse ratePharmacy(RateRequest rateRequest) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setPharmacy(pharmacy);
        Rating savedRating = _ratingRepository.save(rating);
        pharmacy.getRatings().add(savedRating);
        _pharmacyRepository.save(pharmacy);
        return mapRatingToRatingResponse(savedRating);
    }

    private RatingResponse mapRatingToRatingResponse(Rating savedRating) {
        RatingResponse response = new RatingResponse();
        response.setId(savedRating.getId());
        return response;
    }

    @Override
    public RatingResponse rateMedicament(RateRequest rateRequest) {
        Medicament medicament = _medicamentRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setMedicament(medicament);
        Rating savedRating = _ratingRepository.save(rating);
        medicament.getRatings().add(savedRating);
        _medicamentRepository.save(medicament);
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public RatingResponse ratePharmacist(RateRequest rateRequest) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setPharmacist(pharmacist);
        Rating savedRating = _ratingRepository.save(rating);
        pharmacist.getRatings().add(savedRating);
        _pharmacistRepository.save(pharmacist);
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public RatingResponse rateDermatologist(RateRequest rateRequest) {
        Dermatologist dermatologist = _dermatologistRepository.findOneById(rateRequest.getId());
        Rating rating = new Rating();
        rating.setGrade(rateRequest.getGrade());
        rating.setDermatologist(dermatologist);
        Rating savedRating = _ratingRepository.save(rating);
        dermatologist.getRatings().add(savedRating);
        _dermatologistRepository.save(dermatologist);
        return mapRatingToRatingResponse(savedRating);
    }
}
