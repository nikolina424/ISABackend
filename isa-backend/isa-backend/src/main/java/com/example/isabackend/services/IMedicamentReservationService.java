package com.example.isabackend.services;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.MedicamentReservationRequest;
import com.example.isabackend.dto.request.RemoveFromPharmacyRequest;
import com.example.isabackend.dto.response.MedicamentReservationResponse;

import java.util.List;

public interface IMedicamentReservationService {
    List<MedicamentReservationResponse> getDroppedByPatientId(Long id);

    List<MedicamentReservationResponse> getActiveByPatientId(Long id);

    boolean cancelReservation(GetIdRequest request);

    MedicamentReservationResponse createReservation(MedicamentReservationRequest request);

}
