package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;

import java.util.List;

public interface IDermatologistExaminationService {

    List<DermatologistExaminationResponse> getAllAvailableExaminationsByPharmacyId(Long id);

    DermatologistExaminationResponse createAvailableExamination(CreateAvailableExaminationRequest request);
}
