package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.CreatePromotionRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.SubscribePatientRequest;
import com.example.isabackend.dto.response.PromotionResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.repository.IPromotionRepository;
import com.example.isabackend.services.IPromotionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    private final IPromotionRepository _promotionRepository;
    private final IPatientRepository _patientRepository;
    private final IPharmacyRepository _pharmacyRepository;
    private final PatientService _patientService;
    private final EmailService _emailService;
    private final PharmacyService _pharmacyService;

    public PromotionService(IPromotionRepository promotionRepository, IPatientRepository patientRepository, IPharmacyRepository pharmacyRepository, PatientService patientService, EmailService emailService, PharmacyService pharmacyService) {
        _promotionRepository = promotionRepository;
        _patientRepository = patientRepository;
        _pharmacyRepository = pharmacyRepository;
        _patientService = patientService;
        _emailService = emailService;
        _pharmacyService = pharmacyService;
    }

    @Override
    public void subscribePatient(Long id, SubscribePatientRequest request) {
        Patient patient = _patientRepository.findOneById(id);
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        Collection<Pharmacy> pharmacyList = patient.getPharmacies();
        pharmacyList.add(pharmacy);
        List<Patient> patients = pharmacy.getPatients();
        patients.add(patient);
        _pharmacyRepository.save(pharmacy);
        _patientRepository.save(patient);
    }

    @Override
    public PromotionResponse createPromotion(CreatePromotionRequest request) {
        Promotion promotion = new Promotion();
        promotion.setActive(true);
        promotion.setDescription(request.getDescription());
        LocalDate date = LocalDate.parse(request.getExpireDate());
        promotion.setExpireDate(date);
        promotion.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));

        List<Patient> patients =_patientService.getAllSubscribedPatient(request.getPharmacyId());
        for(Patient patient: patients){
            _emailService.sendPromotion(patient, request.getDescription());
        }
        return mapPromotionToPromotionResponse(promotion);
    }

    @Override
    public List<PromotionResponse> getAllByPatientId(Long id) {
        Patient patient = _patientRepository.findOneById(id);
        List<Promotion> promotions = _promotionRepository.findAll();
        List<Promotion> finalPromotions = new ArrayList<>();
        for(Promotion p: promotions){
            if(p.getPharmacy().getPatients().contains(patient)){
                finalPromotions.add(p);
            }
        }
        return mapPromotionsToPromotionResponses(finalPromotions);
    }

    private List<PromotionResponse> mapPromotionsToPromotionResponses(List<Promotion> finalPromotions) {
        List<PromotionResponse> promotionResponses = new ArrayList<>();
        for(Promotion promotion: finalPromotions){
            PromotionResponse response = mapPromotionToPromotionResponse(promotion);
            promotionResponses.add(response);
        }
        return promotionResponses;
    }

    @Override
    public void cancelSubscription(Long id, GetIdRequest request) {
        Patient patient = _patientRepository.findOneById(id);
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getId());
        Collection<Pharmacy> pharmacyList = patient.getPharmacies();
        pharmacyList.remove(pharmacy);
        List<Patient> patients = pharmacy.getPatients();
        patients.remove(patient);
        _pharmacyRepository.save(pharmacy);
        _patientRepository.save(patient);
    }

    private PromotionResponse mapPromotionToPromotionResponse(Promotion promotion) {
        PromotionResponse promotionResponse = new PromotionResponse();
        promotionResponse.setId(promotion.getId());
        promotionResponse.setActive(promotion.isActive());
        promotionResponse.setDescription(promotion.getDescription());
        promotionResponse.setExpireDate(promotion.getExpireDate());
        promotionResponse.setPharmacyResponse(_pharmacyService.mapPharmacyToPharmacyResponse(promotion.getPharmacy()));
        return promotionResponse;
    }
}
