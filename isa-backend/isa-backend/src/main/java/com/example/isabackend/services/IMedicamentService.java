package com.example.isabackend.services;

import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.SearchMedicamentResponse;

import java.util.List;

public interface IMedicamentService {
    List<MedicamentResponse> getAllMedicaments();

    SearchMedicamentResponse searchMedicaments(String name, String type);

    MedicamentResponse getMedicamentById(Long id);

    List<MedicamentResponse> getMedicamentsDoesntBelongToPharmacy(Long id);
}
