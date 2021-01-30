package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;

import java.util.List;

public interface IDermatologistExaminationService {
    DermatologistExaminationResponse createAvailableExamination(String startTimeExamination,String endTimeExamination, String dateExamination, Long pharmacyId, Long dermatologistId, Double price );

    List<DermatologistExaminationResponse> getAllAvailableExaminationsByPharmacyId(Long id);
}
