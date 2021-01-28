package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.SearchMedicamentResponse;
import com.example.isabackend.entity.Medicament;
import com.example.isabackend.entity.Pharmacy;
import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.services.IMedicamentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentService implements IMedicamentService {

    private final IMedicamentRepository _medicamentRepository;
    private final IPharmacyRepository _pharmacyRepository;

    public MedicamentService(IMedicamentRepository medicamentRepository, IPharmacyRepository pharmacyRepository) {
        _medicamentRepository = medicamentRepository;
        _pharmacyRepository = pharmacyRepository;
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

    @Override
    public SearchMedicamentResponse searchMedicaments(String name, String type) {
        List<Medicament> filteredMedicaments = filteredMedicaments(name, type);
        List<MedicamentResponse> medicamentResponses =  mapMedicamentListToMedicamentResponseList(filteredMedicaments);
        return mapToSearchResponse(medicamentResponses);
    }

    @Override
    public MedicamentResponse getMedicamentById(Long id) {
        Medicament medicament = _medicamentRepository.findOneById(id);
        if(medicament != null) {
            return mapMedicamentToMedicamentResponse(medicament);
        } else {
            return null;
        }
    }

    @Override
    public List<MedicamentResponse> getMedicamentsDoesntBelongToPharmacy(Long id) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(id);
        List<MedicamentResponse> finalList = new ArrayList<>();
        List<PharmacyMedicament> pharmacyMedicaments = pharmacy.getPharmacistMedicaments();
        List<Medicament> allMedicaments = _medicamentRepository.findAll();
        System.out.println(pharmacyMedicaments);
        System.out.println("Ovo su moji lekovi");
        for(Medicament m: allMedicaments){
            if(!pharmacyMedicaments.contains(m)){
                finalList.add((mapMedicamentToMedicamentResponse(m)));
            }
        }
        return finalList;
    }

    private SearchMedicamentResponse mapToSearchResponse(List<MedicamentResponse> medicamentResponses) {
        SearchMedicamentResponse searchResponse = new SearchMedicamentResponse();
        searchResponse.setMedicamentResponses(medicamentResponses);
        return searchResponse;
    }

    private List<Medicament> filteredMedicaments(String name, String type) {
        List<Medicament> allMedicaments = _medicamentRepository.findAll();
        return allMedicaments
                .stream()
                .filter(medicament -> {
                    if(name != "") {
                        return medicament.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(medicament -> {
                    if(type != "") {
                        return medicament.getType().equals(type);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
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
