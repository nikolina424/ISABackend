package com.example.isabackend.services;

import com.example.isabackend.dto.request.AddMedicamentToPharmacyRequest;
import com.example.isabackend.dto.request.RemoveFromPharmacyRequest;
import com.example.isabackend.dto.request.UpdatePharmacyMedicamentRequest;
import com.example.isabackend.dto.response.PharmacyMedicamentResponse;
import com.example.isabackend.dto.response.SearchMedicamentResponse;
import com.example.isabackend.dto.response.SearchPharmacyMedicamentResponse;

import java.util.List;

public interface IPharmacyMedicamentService {
    PharmacyMedicamentResponse addMedicamentToPharmacy(AddMedicamentToPharmacyRequest shiftRequest);

    List<PharmacyMedicamentResponse> getAllMedicamentsByPharmacyId(Long id);

    SearchPharmacyMedicamentResponse searchPharmacyMedicaments(String name, String type);

    PharmacyMedicamentResponse getPharmacyMedicament(Long id);

    void updatePharmacyMedicament(Long id, UpdatePharmacyMedicamentRequest request);

    boolean removeMedicamentFromPharmacy(RemoveFromPharmacyRequest request);
}
