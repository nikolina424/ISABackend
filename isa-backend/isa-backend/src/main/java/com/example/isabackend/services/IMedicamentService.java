package com.example.isabackend.services;

import com.example.isabackend.dto.response.MedicamentResponse;

import java.util.List;

public interface IMedicamentService {
    List<MedicamentResponse> getAllMedicaments();
}
