package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.PharmacistRequest;
import com.example.isabackend.dto.request.UpdatePharmacistRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IPharmacistRepository;
import com.example.isabackend.services.IPharmacistService;
import com.example.isabackend.util.GeneralException;
import com.example.isabackend.util.enums.RequestStatus;
import com.example.isabackend.util.enums.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {

    private final IPharmacistRepository _pharmacistRepository;

    public PharmacistService(IPharmacistRepository pharmacistRepository) {
        _pharmacistRepository = pharmacistRepository;
    }

    @Override
    public SearchPharmacistResponse searchPharmacist(String firstName, String lastName) {
        List<Pharmacist> filteredPharmacist = filteredPharmacist(firstName, lastName);
        List<PharmacistResponse> pharmacistResponses =  mapPharmacistsToPharmacistResponses(filteredPharmacist);
        return mapToSearchResponse(pharmacistResponses);
    }

    @Override
    public List<PharmacistResponse> getAllPharmacistByPharmacyId(Long id) {
        List<Pharmacist> allPharmacists = _pharmacistRepository.findAll();
        List<PharmacistResponse> pharmacyPharmacist = new ArrayList<>();
        for (Pharmacist pharmacist: allPharmacists) {
            if(pharmacist.getPharmacy().getId().equals(id)){
                pharmacyPharmacist.add(mapPharmacistToPharmacistResponse(pharmacist));
            }
        }
        return pharmacyPharmacist;
    }

    @Override
    public void updatePharmacist(Long id, UpdatePharmacistRequest request) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(id);
        if(request.getAddress() != null)
            pharmacist.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            pharmacist.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            pharmacist.setLastName(request.getLastName());
        if(request.getNumber() != null)
            pharmacist.setNumber(request.getNumber());


        _pharmacistRepository.save(pharmacist);
    }

    @Override
    public PharmacistResponse getPharmacist(Long id) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(id);
        if(pharmacist != null) {
            return mapPharmacistToPharmacistResponse(pharmacist);
        } else {
            return null;
        }
    }


    private SearchPharmacistResponse mapToSearchResponse(List<PharmacistResponse> pharmacistResponses) {
        SearchPharmacistResponse searchResponse = new SearchPharmacistResponse();
        searchResponse.setPharmacistResponses(pharmacistResponses);
        return searchResponse;
    }

    private List<PharmacistResponse> mapPharmacistsToPharmacistResponses(List<Pharmacist> pharmacists) {
        List<PharmacistResponse> pharmacistResponses = new ArrayList<>();
        for(Pharmacist pharmacist: pharmacists){
            PharmacistResponse response = mapPharmacistToPharmacistResponse(pharmacist);
            pharmacistResponses.add(response);
        }
        return pharmacistResponses;
    }

    private PharmacistResponse mapPharmacistToPharmacistResponse(Pharmacist pharmacist) {
        PharmacistResponse pharmacistResponse = new PharmacistResponse();
        pharmacistResponse.setId(pharmacist.getId());
        pharmacistResponse.setAddress(pharmacist.getAddress());
        pharmacistResponse.setFirstName(pharmacist.getFirstName());
        pharmacistResponse.setLastName(pharmacist.getLastName());
        pharmacistResponse.setUsername(pharmacist.getUser().getUsername());
        pharmacistResponse.setNumber(pharmacist.getNumber());

        float average = 0;
        float sum = 0;
        float counter = 0;
        List<Rating> ratings = pharmacist.getRatings();
        if(ratings.isEmpty()){
            average = 5;
        }else{
            for(Rating r: ratings){
                sum += r.getGrade();
                counter++;
            }
            average = sum/counter;

        }
        pharmacistResponse.setRating(average);

        return pharmacistResponse;
    }

    private List<Pharmacist> filteredPharmacist(String firstName, String lastName) {
        List<Pharmacist> allPharmacists = _pharmacistRepository.findAll();
        return allPharmacists
                .stream()
                .filter(pharmacist -> {
                    if(firstName != "") {
                        return pharmacist.getFirstName().equals(firstName);
                    } else {
                        return true;
                    }
                })
                .filter(pharmacist -> {
                    if(lastName != "") {
                        return pharmacist.getLastName().equals(lastName);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

}
