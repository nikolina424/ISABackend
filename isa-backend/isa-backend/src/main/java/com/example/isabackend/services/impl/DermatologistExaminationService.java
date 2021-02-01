package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.ReserveDermatologistExaminationRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.entity.Dermatologist;
import com.example.isabackend.entity.DermatologistExamination;
import com.example.isabackend.entity.MedicamentReservation;
import com.example.isabackend.repository.IDermatologistExaminationRepository;
import com.example.isabackend.repository.IDermatologistRepository;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IDermatologistExaminationService;
import com.example.isabackend.util.enums.ExaminationStatus;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistExaminationService implements IDermatologistExaminationService {
    private final IDermatologistExaminationRepository _dermatologistExaminationRepository;
    private final ShiftService _shiftService;
    private final IDermatologistRepository _dermatologistRepository;
    private final IPharmacyRepository _pharmacyRepository;
    private final DermatologistService _dermatologistService;
    private final IPatientRepository _patientRepository;
    private final EmailService _emailService;
    private final PatientService _patientService;
    private final  PharmacyService _pharmacyService;

    public DermatologistExaminationService(IDermatologistExaminationRepository dermatologistExaminationRepository, ShiftService shiftService, IDermatologistRepository dermatologistRepository, IPharmacyRepository pharmacyRepository, DermatologistService dermatologistService, IPatientRepository patientRepository, EmailService emailService, PatientService patientService, PharmacyService pharmacyService) {
        _dermatologistExaminationRepository = dermatologistExaminationRepository;
        _shiftService = shiftService;
        _dermatologistRepository = dermatologistRepository;
        _pharmacyRepository = pharmacyRepository;
        _dermatologistService = dermatologistService;
        _patientRepository = patientRepository;
        _emailService = emailService;
        _patientService = patientService;
        _pharmacyService = pharmacyService;
    }

    private List<DermatologistExaminationResponse> mapExaminationsToExaminationResponses(List<DermatologistExamination> all) {
        List<DermatologistExaminationResponse> responses = new ArrayList<>();
        for(DermatologistExamination dermatologistExamination: all){
            DermatologistExaminationResponse response = mapExaminationToExaminationResponse(dermatologistExamination);
            responses.add(response);
        }
        return responses;
    }

    private DermatologistExaminationResponse mapExaminationToExaminationResponse(DermatologistExamination dermatologistExamination) {
        DermatologistExaminationResponse response = new DermatologistExaminationResponse();
        response.setId(dermatologistExamination.getId());
        response.setDateExamination(dermatologistExamination.getDateExamination());
        response.setStartTimeExamination(dermatologistExamination.getStartTimeExamination());
        response.setEndTimeExamination(dermatologistExamination.getEndTimeExamination());
        response.setDermatologist(_dermatologistService.mapDermatologistToDermatologistResponse(dermatologistExamination.getDermatologist()));
        response.setPharmacy(_pharmacyService.mapPharmacyToPharmacyResponse(dermatologistExamination.getPharmacy()));
        response.setPrice(dermatologistExamination.getPrice());
        if(dermatologistExamination.getPatient() != null){
            response.setPatientId(dermatologistExamination.getPatient().getId());
        }

        response.setExaminationStatus(dermatologistExamination.getExaminationStatus().toString());
        return response;
    }

    @Override
    public List<DermatologistExaminationResponse> getAllAvailableExaminationsByPharmacyId(Long id) {
       List<DermatologistExamination> dermatologistExaminations = _dermatologistExaminationRepository.findAllByPharmacy_Id(id);
       List<DermatologistExamination> finalEx = new ArrayList<>();
       for(DermatologistExamination examination: dermatologistExaminations){
           if(examination.getExaminationStatus().equals(ExaminationStatus.AVAILABLE)){
               finalEx.add(examination);
           }
       }
       return mapExaminationsToExaminationResponses(finalEx);
    }

    @Override
    public DermatologistExaminationResponse createAvailableExamination(CreateAvailableExaminationRequest request) {
        List<DermatologistExaminationResponse> allExaminations = mapExaminationsToExaminationResponses(_dermatologistExaminationRepository.findAll());
        List<DermatologistExaminationResponse> myExaminationsInOneHospital = new ArrayList<>();

        LocalDate dateString = LocalDate.parse(request.getDateExamination());
        LocalTime startString = LocalTime.parse(request.getStartTimeExamination());
        LocalTime endString = LocalTime.parse(request.getEndTimeExamination());
        startString = startString.plusHours(1);
        endString = endString.plusHours(1);

        ShiftResponse shiftResponse = _shiftService.getShiftOneDermatologOnePharmacy(request.getDermatologistId(),request.getPharmacyId());
        for(DermatologistExaminationResponse response: allExaminations) {
            if (response.getDermatologist().getId().equals(request.getDermatologistId())) {
                if (response.getPharmacy().getId().equals(request.getPharmacyId())) {
                    myExaminationsInOneHospital.add(response);
                }
            }
        }

        if(startString.isBefore(shiftResponse.getStartShift())){

            System.out.println("Moj novi start je pre starog starta");
            System.out.println(startString);
            System.out.println("je pre");
            System.out.println(shiftResponse.getStartShift());
            return null;
        }else if(endString.isAfter(shiftResponse.getEndShift())){
            System.out.println("Moj novi kraj je posle starog kraja");
            System.out.println(endString);
            System.out.println("je posle");
            System.out.println(shiftResponse.getEndShift());
            return null;
        }

        for(DermatologistExaminationResponse response: myExaminationsInOneHospital){
            if(response.getDateExamination().isEqual(dateString)){
                if(startString.isBefore(response.getStartTimeExamination())){
                    if(endString.isAfter(response.getStartTimeExamination())){
                        System.out.println("Prekidam preklapaju se");
                        return null;

                    }
                    //OK JE JER JE PRE POCETKA TRENUTNE
                }else if(startString.isBefore(response.getEndTimeExamination())){
                    //counter povecaj preklapaju se
                    System.out.println("Prekidam preklapaju se");
                    return null;
                }
            }
        }

        DermatologistExamination finalEx = new DermatologistExamination();
        finalEx.setExaminationStatus(ExaminationStatus.AVAILABLE);
        finalEx.setEndTimeExamination(endString);
        finalEx.setStartTimeExamination(startString);
        finalEx.setPrice(request.getPrice());
        finalEx.setDateExamination(dateString);
        finalEx.setDermatologist(_dermatologistRepository.findOneById(request.getDermatologistId()));
        finalEx.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));
        _dermatologistExaminationRepository.save(finalEx);
        return mapExaminationToExaminationResponse(finalEx);
    }

    @Override
    public boolean reserveExamination(ReserveDermatologistExaminationRequest request) {
        DermatologistExamination dermatologistExamination = _dermatologistExaminationRepository.findOneById(request.getReservationId());
        dermatologistExamination.setPatient(_patientRepository.findOneById(request.getPatientId()));
        dermatologistExamination.setExaminationStatus(ExaminationStatus.RESERVED);
        DermatologistExamination savedReservation = _dermatologistExaminationRepository.save(dermatologistExamination);
        _emailService.approveDermatologistExaminationReservation(savedReservation);
        return true;
    }

    @Override
    public List<DermatologistExaminationResponse> getAllDroppedReservationByPatientId(Long id) {
        List<DermatologistExamination> finalEx = new ArrayList<>();
        List<DermatologistExamination> patientExaminations = _dermatologistExaminationRepository.findAllByPatient_Id(id);
        for(DermatologistExamination examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.DROPPED)){
                finalEx.add(examination);
            }
        }
        return mapExaminationsToExaminationResponses(finalEx);
    }

    @Override
    public List<DermatologistExaminationResponse> getAllActiveReservationByPatientId(Long id) {
        List<DermatologistExamination> finalEx = new ArrayList<>();
        List<DermatologistExamination> patientExaminations = _dermatologistExaminationRepository.findAllByPatient_Id(id);
        for(DermatologistExamination examination: patientExaminations){
            if(examination.getExaminationStatus().equals(ExaminationStatus.RESERVED)){
                finalEx.add(examination);
            }
        }
        return mapExaminationsToExaminationResponses(finalEx);
    }

    @Override
    public boolean cancelReservation(GetIdRequest request) {
        DermatologistExamination dermatologistExamination = _dermatologistExaminationRepository.findOneById(request.getId());
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate dateExamination = dermatologistExamination.getDateExamination();
        System.out.println(dateExamination);
        LocalDate pickupDate24Hours = dateExamination.minusDays(1);
        System.out.println(pickupDate24Hours);
        if(now.isBefore(pickupDate24Hours)){
            dermatologistExamination.setExaminationStatus(ExaminationStatus.AVAILABLE);
            _dermatologistExaminationRepository.save(dermatologistExamination);
            return true;
        }
        return false;
    }
}
