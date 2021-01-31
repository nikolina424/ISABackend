package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.MedicamentReservationRequest;
import com.example.isabackend.dto.response.MedicamentReservationResponse;
import com.example.isabackend.entity.MedicamentReservation;
import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.repository.IMedicamentReservationRepository;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.repository.IPharmacyMedicamentRepository;
import com.example.isabackend.services.IMedicamentReservationService;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentReservationService implements IMedicamentReservationService {

    private final IMedicamentReservationRepository _medicamentReservationRepository;
    private final PharmacyMedicamentService _pharmacyMedicamentService;
    private final IPatientRepository _patientRepository;
    private final IPharmacyMedicamentRepository _pharmacyMedicamentRepository;
    private final EmailService _emailService;

    public MedicamentReservationService(IMedicamentReservationRepository medicamentReservationRepository, PharmacyMedicamentService pharmacyMedicamentService, IPatientRepository patientRepository, IPharmacyMedicamentRepository pharmacyMedicamentRepository, EmailService emailService) {
        _medicamentReservationRepository = medicamentReservationRepository;
        _pharmacyMedicamentService = pharmacyMedicamentService;
        _patientRepository = patientRepository;
        _pharmacyMedicamentRepository = pharmacyMedicamentRepository;
        _emailService = emailService;
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
            if(m.getMedicamentReservationStatus().equals(MedicamentReservationStatus.RESERVED) || m.getMedicamentReservationStatus().equals(MedicamentReservationStatus.APPROVED)){
                MedicamentReservationResponse medicamentResponse = mapMedicamentReservationToMedicamentReservationResponse(m);
                responses.add(medicamentResponse);
            }

        }
        return responses;
    }

    @Override
    public boolean cancelReservation(GetIdRequest request) {
        MedicamentReservation medicamentReservation = _medicamentReservationRepository.findOneById(request.getId());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate pickupDate = medicamentReservation.getDateToPick();
        System.out.println(pickupDate);
        LocalDate pickupDate24Hours = pickupDate.minusDays(1);
        System.out.println(pickupDate24Hours);
        if(now.isBefore(pickupDate24Hours)){
            medicamentReservation.setMedicamentReservationStatus(MedicamentReservationStatus.CANCELED);
            _medicamentReservationRepository.save(medicamentReservation);
            return true;
        }
        return false;
    }

    @Override
    public MedicamentReservationResponse createReservation(MedicamentReservationRequest request) {
        MedicamentReservation medicamentReservation = new MedicamentReservation();
        medicamentReservation.setMedicamentReservationStatus(MedicamentReservationStatus.RESERVED);
        LocalDate date = LocalDate.parse(request.getDateToPick());
        medicamentReservation.setDateToPick(date);

        PharmacyMedicament pharmacyMedicament = _pharmacyMedicamentRepository.findOneById(request.getPharmacyMedicamentId());
        int quantity = pharmacyMedicament.getQuantity() - 1;

        medicamentReservation.setPatient(_patientRepository.findOneById(request.getPatientId()));
        medicamentReservation.setPharmacyMedicament(pharmacyMedicament);
        MedicamentReservation savedReservation = _medicamentReservationRepository.save(medicamentReservation);

        pharmacyMedicament.setQuantity(quantity);
        _pharmacyMedicamentRepository.save(pharmacyMedicament);

        _emailService.approveMedicamentReservation(savedReservation);
        return mapMedicamentReservationToMedicamentReservationResponse(savedReservation);
    }
}
