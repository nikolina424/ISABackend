package com.example.isabackend.services;

import com.example.isabackend.controller.AllergyRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;

import java.util.List;

public interface IPatientService {
    List<PatientResponse> getAllPatients();

    PatientResponse getPatientById(Long id);

    void updatePatient(Long id, UpdatePatientRequest request);

    boolean deletePatientById(Long id);

    void approveRegistrationRequest(GetIdRequest request);

    void denyRegistrationRequest(GetIdRequest request);

    void confirmRegistrationRequest(GetIdRequest request);

    List<PatientResponse> getRegistrationRequests();

    void addNewAlergy(Long id, AllergyRequest request);

    List<MedicamentResponse> getAvailableMeds(Long id);
}
