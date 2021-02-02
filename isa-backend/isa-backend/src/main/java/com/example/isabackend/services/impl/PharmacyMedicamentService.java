package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.AddMedicamentToPharmacyRequest;
import com.example.isabackend.dto.request.RemoveFromPharmacyRequest;
import com.example.isabackend.dto.request.UpdatePharmacyMedicamentRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.repository.IMedicamentReservationRepository;
import com.example.isabackend.repository.IPharmacyMedicamentRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IPharmacyMedicamentService;
import com.example.isabackend.util.enums.ExaminationStatus;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyMedicamentService implements IPharmacyMedicamentService {
    private final IPharmacyMedicamentRepository _pharmacyMedicamentRepository;
    private final MedicamentService _medicamentService;
    private final PharmacyService _pharmacyService;
    private final IPharmacyRepository _pharmacyRepository;
    private final IMedicamentRepository _medicamentRepository;
    private final IMedicamentReservationRepository _medicamentReservationRepository;

    public PharmacyMedicamentService(IPharmacyMedicamentRepository pharmacyMedicamentRepository, MedicamentService medicamentService, PharmacyService pharmacyService, IPharmacyRepository pharmacyRepository, IMedicamentRepository medicamentRepository, IMedicamentReservationRepository medicamentReservationRepository) {
        _pharmacyMedicamentRepository = pharmacyMedicamentRepository;
        _medicamentService = medicamentService;
        _pharmacyService = pharmacyService;
        _pharmacyRepository = pharmacyRepository;
        _medicamentRepository = medicamentRepository;
        _medicamentReservationRepository = medicamentReservationRepository;
    }

    @Override
    public PharmacyMedicamentResponse addMedicamentToPharmacy(AddMedicamentToPharmacyRequest request) {
        PharmacyMedicament pharmacyMedicament = new PharmacyMedicament();
        pharmacyMedicament.setMedicament(_medicamentRepository.findOneById(request.getMedicamentId()));
        pharmacyMedicament.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));
        pharmacyMedicament.setQuantity(request.getQuantity());
        _pharmacyMedicamentRepository.save(pharmacyMedicament);
        return mapPharmacyMedicamentToPharmacyMedicamentResponse(pharmacyMedicament);

    }

    @Override
    public List<PharmacyMedicamentResponse> getAllMedicamentsByPharmacyId(Long id) {
        List<PharmacyMedicament> allMedicaments = _pharmacyMedicamentRepository.findAll();
        List<PharmacyMedicamentResponse> pharmacyMedicamentResponses = new ArrayList<>();
        for (PharmacyMedicament pharmacyMedicament: allMedicaments) {
            if(pharmacyMedicament.getPharmacy().getId() == id){
                pharmacyMedicamentResponses.add(mapPharmacyMedicamentToPharmacyMedicamentResponse(pharmacyMedicament));
            }
        }
        return pharmacyMedicamentResponses;
    }

    @Override
    public SearchPharmacyMedicamentResponse searchPharmacyMedicaments(String name, String type) {
        List<PharmacyMedicament> filteredMedicaments = filteredMedicaments(name, type);
        List<PharmacyMedicamentResponse> medicamentResponses =  mapPharmacyMedicamentListToPharmacyMedicamentResponseList(filteredMedicaments);
        return mapToSearchResponse(medicamentResponses);
    }

    @Override
    public PharmacyMedicamentResponse getPharmacyMedicament(Long id) {
        PharmacyMedicament pharmacyMedicament = _pharmacyMedicamentRepository.findOneById(id);
        if(pharmacyMedicament != null) {
            return mapPharmacyMedicamentToPharmacyMedicamentResponse(pharmacyMedicament);
        } else {
            return null;
        }
    }

    @Override
    public void updatePharmacyMedicament(Long id, UpdatePharmacyMedicamentRequest request) {
        PharmacyMedicament pharmacyMedicament = _pharmacyMedicamentRepository.findOneById(id);
        if (request.getQuantity() != 0)
            pharmacyMedicament.setQuantity(request.getQuantity());
        _pharmacyMedicamentRepository.save(pharmacyMedicament);
    }

    private List<PharmacyMedicamentResponse> mapPharmacyMedicamentListToPharmacyMedicamentResponseList(List<PharmacyMedicament> pharmacyMedicaments) {
        List<PharmacyMedicamentResponse> pharmacyMedicamentResponses = new ArrayList<>();
        for (PharmacyMedicament m:pharmacyMedicaments) {
            pharmacyMedicamentResponses.add(mapPharmacyMedicamentToPharmacyMedicamentResponse(m));
        }
        return  pharmacyMedicamentResponses;

    }

    private SearchPharmacyMedicamentResponse mapToSearchResponse(List<PharmacyMedicamentResponse> medicamentResponses) {
        SearchPharmacyMedicamentResponse searchResponse = new SearchPharmacyMedicamentResponse();
        searchResponse.setPharmacyMedicamentResponses(medicamentResponses);
        return searchResponse;
    }

    private List<PharmacyMedicament> filteredMedicaments(String name, String type) {
        List<PharmacyMedicament> allPharmacyMedicaments = _pharmacyMedicamentRepository.findAll();
        return allPharmacyMedicaments
                .stream()
                .filter(pharmacyMedicament -> {
                    if(name != "") {
                        return pharmacyMedicament.getMedicament().getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(pharmacyMedicament -> {
                    if(type != "") {
                        return pharmacyMedicament.getMedicament().getType().equals(type);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeMedicamentFromPharmacy(RemoveFromPharmacyRequest request) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        System.out.println(pharmacy.getName());
        System.out.println("Ovo je id apoteke iz koje hocu da izbrisem lek");
        System.out.println(pharmacy.getId());
        PharmacyMedicament pharmacyMedicament = _pharmacyMedicamentRepository.findOneById(request.getItemId());
        System.out.println("Ovo je lek koji zelim da izbacim");
        System.out.println(pharmacyMedicament.getMedicament().getName());

        List<MedicamentReservation> medicamentReservations = _medicamentReservationRepository.findAll();
        List<MedicamentReservation> pharmacyMedicamentReservation = new ArrayList<>();
        for(MedicamentReservation mr: medicamentReservations){
            if(mr.getPharmacyMedicament().getId() == pharmacyMedicament.getId()){
                pharmacyMedicamentReservation.add(mr);
                System.out.println(mr.getMedicamentReservationStatus());
                System.out.println("usao sam jer imam isti id apoteke");
            }
        }
        for(MedicamentReservation mr: pharmacyMedicamentReservation){
            System.out.println("ovo mi je status");
            System.out.println(mr.getMedicamentReservationStatus().toString());
                if(mr.getMedicamentReservationStatus().equals(MedicamentReservationStatus.RESERVED)){
                    System.out.println("status mi je reserved tako da prekidam fju");
                    return false;
                }

        }
        _pharmacyMedicamentRepository.delete(pharmacyMedicament);
        return true;
    }

    public PharmacyMedicamentResponse mapPharmacyMedicamentToPharmacyMedicamentResponse(PharmacyMedicament pharmacyMedicament) {
        PharmacyMedicamentResponse pharmacyMedicamentResponse = new PharmacyMedicamentResponse();
        pharmacyMedicamentResponse.setId(pharmacyMedicament.getId());
        pharmacyMedicamentResponse.setQuantity(pharmacyMedicament.getQuantity());
        pharmacyMedicamentResponse.setMedicament(_medicamentService.mapMedicamentToMedicamentResponse(pharmacyMedicament.getMedicament()));
        pharmacyMedicamentResponse.setPharmacy(_pharmacyService.mapPharmacyToPharmacyResponse(pharmacyMedicament.getPharmacy()));
        return pharmacyMedicamentResponse;
    }
}
