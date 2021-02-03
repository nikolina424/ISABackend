package com.example.isabackend.services;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.response.PharmacistExaminationResponse;

import java.util.List;

public interface IPharmacistExaminationService {
    List<PharmacistExaminationResponse> getAllDroppedReservationByPatientId(Long id);

    List<PharmacistExaminationResponse> getAllActiveReservationsByPatientId(Long id);

    boolean cancelReservation(GetIdRequest request);
}
