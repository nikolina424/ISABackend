package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.PharmacyResponse;
import com.example.isabackend.dto.response.SearchDermatologistResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.entity.Dermatologist;
import com.example.isabackend.repository.IDermatologistRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IDermatologistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DermatologistService implements IDermatologistService {

    private final IDermatologistRepository _dermatologistRepository;
    private final PharmacyService _pharmacyService;
    private final ShiftService _shiftService;

    public DermatologistService(IDermatologistRepository dermatologistRepository, PharmacyService pharmacyService, ShiftService shiftService) {
        _dermatologistRepository = dermatologistRepository;
        _pharmacyService = pharmacyService;
        _shiftService = shiftService;
    }

    @Override
    public List<DermatologistResponse> getAllDermatologists() {
        List<Dermatologist> dermatologists = _dermatologistRepository.findAll();
        List<DermatologistResponse> dermatologistResponses = new ArrayList<>();
        for (Dermatologist dermatologist: dermatologists) {
            DermatologistResponse dermatologistResponse = mapDermatologistToDermatologistResponse(dermatologist);
            dermatologistResponses.add(dermatologistResponse);
        }
        return dermatologistResponses;
    }

    public DermatologistResponse mapDermatologistToDermatologistResponse(Dermatologist dermatologist) {
        DermatologistResponse dermatologistResponse = new DermatologistResponse();
        dermatologistResponse.setId(dermatologist.getId());
        dermatologistResponse.setAddress(dermatologist.getAddress());
        dermatologistResponse.setFirstName(dermatologist.getFirstName());
        dermatologistResponse.setLastName(dermatologist.getLastName());
        dermatologistResponse.setUsername(dermatologist.getUser().getUsername());
        dermatologistResponse.setNumber(dermatologist.getNumber());
        List<ShiftResponse> myShifts = new ArrayList<>();
        List<PharmacyResponse> myPharmacies = new ArrayList<>();
        List<PharmacyResponse> allPharmacies = _pharmacyService.getAllPharmacies();
        List<ShiftResponse> shiftResponses = _shiftService.getAllShifts();
        for (ShiftResponse shift:shiftResponses) {
            if(shift.getDermatologistId() == dermatologist.getId()){
                myShifts.add(shift);
            }
        }
        for(PharmacyResponse pharmacyResponse: allPharmacies){
            for(ShiftResponse myShift: myShifts){
                if(myShift.getPharmacyId().equals(pharmacyResponse.getId())){
                    myPharmacies.add(pharmacyResponse);
                }
            }
        }
        dermatologistResponse.setPharmacyResponses(myPharmacies);
        return dermatologistResponse;
    }

    @Override
    public DermatologistResponse getDermatologistById(Long id) {
        Dermatologist dermatologist = _dermatologistRepository.findOneById(id);
        if(dermatologist != null) {
            return mapDermatologistToDermatologistResponse(dermatologist);
        } else {
            return null;
        }
    }

    @Override
    public List<DermatologistResponse> getAllDermatologistByPharmacyId(Long id) {
        List<Dermatologist> allDermatologists = _dermatologistRepository.findAll();
        List<DermatologistResponse> pharmacyDermatologist = new ArrayList<>();
        List<ShiftResponse> shiftResponses = _shiftService.getAllShifts();
        for (ShiftResponse shiftResponse: shiftResponses) {
            if(shiftResponse.getPharmacyId() == id){
                Dermatologist d = _dermatologistRepository.findOneById(shiftResponse.getDermatologistId());
                pharmacyDermatologist.add(mapDermatologistToDermatologistResponse(d));
            }
        }
        return pharmacyDermatologist;
    }

    @Override
    public SearchDermatologistResponse searchDermatologists(String firstName, String lastName) {

        List<Dermatologist> filteredDermatologists = filteredDermatologists(firstName, lastName);
        List<DermatologistResponse> dermatologistResponses =  mapDermatologistsToDermatologistsResponses(filteredDermatologists);
        return mapToSearchResponse(dermatologistResponses);
    }

    private SearchDermatologistResponse mapToSearchResponse(List<DermatologistResponse> dermatologistResponses) {
        SearchDermatologistResponse searchResponse = new SearchDermatologistResponse();
        searchResponse.setDermatologistResponses(dermatologistResponses);
        return searchResponse;
    }


    private List<DermatologistResponse> mapDermatologistsToDermatologistsResponses(List<Dermatologist> dermatologists) {
        List<DermatologistResponse> dermatologistResponses = new ArrayList<>();
        for(Dermatologist dermatologist: dermatologists){
            DermatologistResponse response = mapDermatologistToDermatologistResponse(dermatologist);
            dermatologistResponses.add(response);
        }
        return dermatologistResponses;
    }

    private List<Dermatologist> filteredDermatologists(String firstName, String lastName) {
        List<Dermatologist> allDermatologists = _dermatologistRepository.findAll();
        return allDermatologists
                .stream()
                .filter(dermatologist -> {
                    if(firstName != "") {
                        return dermatologist.getFirstName().equals(firstName);
                    } else {
                        return true;
                    }
                })
                .filter(dermatologist -> {
                    if(lastName != "") {
                        return dermatologist.getLastName().equals(lastName);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }
}
