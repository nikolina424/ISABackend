package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.PharmacyRequest;
import com.example.isabackend.dto.request.UpdatePharmacyRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.repository.IPharmacyMedicamentRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository _pharmacyRepository;
    private final IMedicamentRepository _medicamentRepository;
    private final IPharmacyMedicamentRepository _phRepository;

    public PharmacyService(IPharmacyRepository pharmacyRepository, IMedicamentRepository medicamentRepository, IPharmacyMedicamentRepository phRepository) {
        _pharmacyRepository = pharmacyRepository;
        _medicamentRepository = medicamentRepository;
        _phRepository = phRepository;
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

    @Override
    public SearchPharmacyResponse searchPharmacies(String name) {
        List<Pharmacy> filteredPharmacies = filteredPharmacies(name);
        List<PharmacyResponse> pharmacyResponses =  mapPharmacyListToPharmacyResponseList(filteredPharmacies);
        return mapToSearchResponse(pharmacyResponses);
    }

    @Override
    public List<PharmacyResponse> getPharmaciesByMedicamentId(Long id) {
        Medicament medicament = _medicamentRepository.findOneById(id);
        List<PharmacyMedicament> pharmacyMedicaments = _phRepository.findAllByMedicament_Id(medicament.getId());
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(PharmacyMedicament pharmacyMedicament: pharmacyMedicaments){
            Pharmacy pharmacy = pharmacyMedicament.getPharmacy();
            pharmacies.add(pharmacy);
        }
        return mapPharmacyListToPharmacyResponseList(pharmacies);
    }

    private SearchPharmacyResponse mapToSearchResponse(List<PharmacyResponse> pharmacyResponses) {
        SearchPharmacyResponse searchResponse = new SearchPharmacyResponse();
        searchResponse.setPharmacyResponses(pharmacyResponses);
        return searchResponse;
    }

    private List<PharmacyResponse> mapPharmacyListToPharmacyResponseList(List<Pharmacy> pharmacies) {
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for (Pharmacy p:pharmacies) {
            pharmacyResponses.add(mapPharmacyToPharmacyResponse(p));
        }
        return  pharmacyResponses;
    }

    private List<Pharmacy> filteredPharmacies(String name) {
        List<Pharmacy> allPharmacies = _pharmacyRepository.findAll();
        return allPharmacies
                .stream()
                .filter(pharmacy -> {
                    if(name != "") {
                        return pharmacy.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    public PharmacyResponse mapPharmacyToPharmacyResponse(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        pharmacyResponse.setId(pharmacy.getId());
        pharmacyResponse.setAddress(pharmacy.getAddress());
        pharmacyResponse.setAbout(pharmacy.getAbout());
        pharmacyResponse.setName(pharmacy.getName());
        float average = 0;
        float sum = 0;
        float counter = 0;
        List<Rating> ratings = pharmacy.getRatings();
        if(ratings.isEmpty()){
            average = 5;
        }else{
            for(Rating r: ratings){
                sum += r.getGrade();
                counter++;
            }
            average = sum/counter;

        }
        pharmacyResponse.setRating(average);
        return pharmacyResponse;
    }

}
