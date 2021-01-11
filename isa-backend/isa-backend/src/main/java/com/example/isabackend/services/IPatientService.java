package com.example.isabackend.services;

import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.response.PatientResponse;

import java.util.List;

public interface IPatientService {
    List<PatientResponse> getAllPatients();

    PatientResponse getPatientById(Long id);

    void updatePatient(Long id, UpdatePatientRequest request);

    boolean deletePatientById(Long id);
}
