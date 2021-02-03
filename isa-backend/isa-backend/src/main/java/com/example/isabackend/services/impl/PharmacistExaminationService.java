package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.response.PharmacistExaminationResponse;
import com.example.isabackend.entity.DermatologistExamination;
import com.example.isabackend.entity.PharmacistExamination;
import com.example.isabackend.repository.IPharmacistExaminationRepository;
import com.example.isabackend.services.IPharmacistExaminationService;
import com.example.isabackend.util.enums.ExaminationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistExaminationService implements IPharmacistExaminationService {
    private final IPharmacistExaminationRepository _peRepository;
    private final ShiftPharmacistService _spService;

    public PharmacistExaminationService(IPharmacistExaminationRepository peRepository, ShiftPharmacistService spService) {
        _peRepository = peRepository;
        _spService = spService;
    }

    @Override
    public List<PharmacistExaminationResponse> getAllDroppedReservationByPatientId(Long id) {
        List<PharmacistExamination> finalEx = new ArrayList<>();
        List<PharmacistExamination> patientExaminations = _peRepository.findAllByPatient_Id(id);
        for(PharmacistExamination examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.DROPPED)){
                finalEx.add(examination);
            }
        }
        return mapExaminationsToExaminationResponses(finalEx);
    }

    @Override
    public List<PharmacistExaminationResponse> getAllActiveReservationsByPatientId(Long id) {
        List<PharmacistExamination> finalEx = new ArrayList<>();
        List<PharmacistExamination> patientExaminations = _peRepository.findAllByPatient_Id(id);
        for(PharmacistExamination examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.RESERVED)){
                finalEx.add(examination);
            }
        }
        return mapExaminationsToExaminationResponses(finalEx);
    }

    @Override
    public boolean cancelReservation(GetIdRequest request) {
        PharmacistExamination pharmacistExamination = _peRepository.findOneById(request.getId());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate dateExamination = pharmacistExamination.getDateExamination();
        System.out.println(dateExamination);
        LocalDate pickupDate24Hours = dateExamination.minusDays(1);
        System.out.println(pickupDate24Hours);
        if(now.isBefore(pickupDate24Hours)){
            _peRepository.delete(pharmacistExamination);
            return true;
        }
        return false;
    }

    public List<PharmacistExaminationResponse> mapExaminationsToExaminationResponses(List<PharmacistExamination> all) {
        List<PharmacistExaminationResponse> responses = new ArrayList<>();
        for(PharmacistExamination pharmacistExamination: all){
            PharmacistExaminationResponse response = mapExaminationToExaminationResponse(pharmacistExamination);
            responses.add(response);
        }
        return responses;
    }

    public PharmacistExaminationResponse mapExaminationToExaminationResponse(PharmacistExamination pharmacistExamination) {
        PharmacistExaminationResponse response = new PharmacistExaminationResponse();
        response.setId(pharmacistExamination.getId());
        response.setDateExamination(pharmacistExamination.getDateExamination());
        response.setStartTimeExamination(pharmacistExamination.getStartTimeExamination());
        response.setEndTimeExamination(pharmacistExamination.getEndTimeExamination());
        response.setPrice(pharmacistExamination.getPrice());
        response.setShiftPharmacistResponse(_spService.mapShiftPharmacistToShiftPharmacistResponse(pharmacistExamination.getShiftPharmacist()));
        response.setPatientId(pharmacistExamination.getPatient().getId());
        response.setExaminationStatus(pharmacistExamination.getExaminationStatus().toString());
        return response;
    }
}
