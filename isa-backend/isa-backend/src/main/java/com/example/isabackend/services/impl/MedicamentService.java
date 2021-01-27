package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.entity.Medicament;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.services.IMedicamentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentService implements IMedicamentService {

    private final IMedicamentRepository _medicamentRepository;

    public MedicamentService(IMedicamentRepository medicamentRepository) {
        _medicamentRepository = medicamentRepository;
    }

    @Override
    public List<MedicamentResponse> getAllMedicaments() {
        List<Medicament> medicaments = _medicamentRepository.findAll();
        List<MedicamentResponse> medicamentResponses = new ArrayList<>();
        for (Medicament medicament: medicaments) {
            MedicamentResponse medicamentResponse = mapMedicamentToMedicamentResponse(medicament);
            medicamentResponses.add(medicamentResponse);
        }
        return medicamentResponses;
    }

    public MedicamentResponse mapMedicamentToMedicamentResponse(Medicament medicament) {
        MedicamentResponse response = new MedicamentResponse();
        response.setId(medicament.getId());
        response.setName(medicament.getName());
        response.setType(medicament.getType());
        response.setContraindications(medicament.getContraindications());
        response.setIngredients(medicament.getIngredients());
        return response;
    }

    public List<MedicamentResponse> mapMedicamentListToMedicamentResponseList(List<Medicament> medicaments){
        List<MedicamentResponse> medicamentResponses = new ArrayList<>();
        for (Medicament m:medicaments) {
           medicamentResponses.add(mapMedicamentToMedicamentResponse(m));
        }
        return  medicamentResponses;
    }
}
