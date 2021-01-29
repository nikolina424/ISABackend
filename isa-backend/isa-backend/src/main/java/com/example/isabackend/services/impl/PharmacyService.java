package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.PharmacyRequest;
import com.example.isabackend.dto.request.UpdatePharmacyRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IPharmacyService;
import com.example.isabackend.util.GeneralException;
import com.example.isabackend.util.enums.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository _pharmacyRepository;

    public PharmacyService(IPharmacyRepository pharmacyRepository) {
        _pharmacyRepository = pharmacyRepository;
    }

    @Override
    public void updatePharmacy(Long id, UpdatePharmacyRequest request) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(id);
        if(request.getAddress() != null)
            pharmacy.setAddress(request.getAddress());
        if(request.getName() != null)
            pharmacy.setName(request.getName());
        if(request.getAbout() != null)
            pharmacy.setAbout(request.getAbout());
        _pharmacyRepository.save(pharmacy);
    }

    @Override
    public PharmacyResponse getPharmacyById(Long id) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(id);
        if(pharmacy != null) {
            return mapPharmacyToPharmacyResponse(pharmacy);
        } else {
            return null;
        }
    }

    @Override
    public List<PharmacyResponse> getAllPharmacies() {
        List<Pharmacy> pharmacies = _pharmacyRepository.findAll();
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies) {
            PharmacyResponse pharmacyResponse = mapPharmacyToPharmacyResponse(pharmacy);
            pharmacyResponses.add(pharmacyResponse);
        }
        return pharmacyResponses;
    }

    @Override
    public boolean registerPharmacy(PharmacyRequest request) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(request.getName());
        pharmacy.setAbout(request.getAbout());
        pharmacy.setAddress(request.getAddress());
        _pharmacyRepository.save(pharmacy);
        return true;
    }

    public PharmacyResponse mapPharmacyToPharmacyResponse(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        pharmacyResponse.setId(pharmacy.getId());
        pharmacyResponse.setAddress(pharmacy.getAddress());
        pharmacyResponse.setAbout(pharmacy.getAbout());
        pharmacyResponse.setName(pharmacy.getName());

        return pharmacyResponse;
    }

}
