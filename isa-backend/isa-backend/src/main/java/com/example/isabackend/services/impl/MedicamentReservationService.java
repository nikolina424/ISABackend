package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.MedicamentReservationResponse;
import com.example.isabackend.entity.MedicamentReservation;
import com.example.isabackend.repository.IMedicamentReservationRepository;
import com.example.isabackend.services.IMedicamentReservationService;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentReservationService implements IMedicamentReservationService {

    private final IMedicamentReservationRepository _medicamentReservationRepository;
    private final PharmacyMedicamentService _pharmacyMedicamentService;

    public MedicamentReservationService(IMedicamentReservationRepository medicamentReservationRepository, PharmacyMedicamentService pharmacyMedicamentService) {
        _medicamentReservationRepository = medicamentReservationRepository;
        _pharmacyMedicamentService = pharmacyMedicamentService;
    }

    @Override
    public List<MedicamentReservationResponse> getDroppedByPatientId(Long id) {
        List<MedicamentReservation> medicamentReservations = _medicamentReservationRepository.findAllByPatient_Id(id);
        List<MedicamentReservationResponse> responses = new ArrayList<>();
        for (MedicamentReservation m: medicamentReservations) {
            if(m.getMedicamentReservationStatus().equals(MedicamentReservationStatus.DROPPED)){
                MedicamentReservationResponse medicamentResponse = mapMedicamentReservationToMedicamentReservationResponse(m);
                responses.add(medicamentResponse);
            }

        }
        return responses;
    }

    private MedicamentReservationResponse mapMedicamentReservationToMedicamentReservationResponse(MedicamentReservation m) {
        MedicamentReservationResponse response = new MedicamentReservationResponse();
        response.setId(m.getId());
        response.setDateToPick(m.getDateToPick());
        response.setMedicamentReservationStatus(m.getMedicamentReservationStatus().toString());
        response.setPatientId(m.getPatient().getId());
        response.setPharmacyMedicament(_pharmacyMedicamentService.mapPharmacyMedicamentToPharmacyMedicamentResponse(m.getPharmacyMedicament()));

        return response;
    }

    @Override
    public List<MedicamentReservationResponse> getActiveByPatientId(Long id) {
        List<MedicamentReservation> medicamentReservations = _medicamentReservationRepository.findAllByPatient_Id(id);
        List<MedicamentReservationResponse> responses = new ArrayList<>();
        for (MedicamentReservation m: medicamentReservations) {
            if(!m.getMedicamentReservationStatus().equals(MedicamentReservationStatus.DROPPED)){
                MedicamentReservationResponse medicamentResponse = mapMedicamentReservationToMedicamentReservationResponse(m);
                responses.add(medicamentResponse);
            }

        }
        return responses;
    }
}
