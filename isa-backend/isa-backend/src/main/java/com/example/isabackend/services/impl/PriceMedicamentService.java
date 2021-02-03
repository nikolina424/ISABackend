package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.PriceMedicamentRequest;
import com.example.isabackend.dto.response.PriceMedicamentResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IPharmacyMedicamentRepository;
import com.example.isabackend.repository.IPriceMedicamentRepository;
import com.example.isabackend.repository.IPricelistRepository;
import com.example.isabackend.services.IPriceMedicamentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceMedicamentService implements IPriceMedicamentService {

    private final IPriceMedicamentRepository _priceMedicamentRepository;
    private final PricelistService _pricelistService;
    private final PharmacyMedicamentService _pharmacyMedicamentService;
    private final IPharmacyMedicamentRepository _pharmacyMedicamentRepository;
    private final IPricelistRepository _pricelistRepository;

    public PriceMedicamentService(IPriceMedicamentRepository priceMedicamentRepository, PricelistService pricelistService, PharmacyMedicamentService pharmacyMedicamentService, IPharmacyMedicamentRepository pharmacyMedicamentRepository, IPricelistRepository pricelistRepository) {
        _priceMedicamentRepository = priceMedicamentRepository;
        _pricelistService = pricelistService;
        _pharmacyMedicamentService = pharmacyMedicamentService;
        _pharmacyMedicamentRepository = pharmacyMedicamentRepository;
        _pricelistRepository = pricelistRepository;
    }

    @Override
    public List<PriceMedicamentResponse> getAllByPharmacyId(Long id) {
        List<PriceMedicament> priceMedicaments = _priceMedicamentRepository.findAll();
        List<PriceMedicament> finalList = new ArrayList<>();
        for(PriceMedicament priceMedicament: priceMedicaments){
            if(priceMedicament.getPricelist().getPharmacy().getId() == id){
                if(priceMedicament.getPricelist().getActive() == true) {
                    finalList.add((priceMedicament));
                }
            }
        }
        return mapPriceMedicamentsToPriceMedicamentResponses(finalList);
    }

    @Override
    public PriceMedicamentResponse createPriceMedicament(PriceMedicamentRequest request) {
        PharmacyMedicament pharmacyMedicament = _pharmacyMedicamentRepository.findOneById(request.getPharmacyMedicamentId());
        PriceMedicament priceMedicament = new PriceMedicament();
        priceMedicament.setPharmacyMedicament(pharmacyMedicament);
        priceMedicament.setPrice(request.getPrice());
        priceMedicament.setPricelist(_pricelistRepository.findOneById(request.getPricelistId()));
        _priceMedicamentRepository.save(priceMedicament);
        return mapPriceMedicamentToPriceMedicamentResponse(priceMedicament);
    }

    private List<PriceMedicamentResponse> mapPriceMedicamentsToPriceMedicamentResponses(List<PriceMedicament> priceMedicaments) {
        PriceMedicamentResponse priceMedicamentResponse = new PriceMedicamentResponse();
        List<PriceMedicamentResponse> priceMedicamentResponses = new ArrayList<>();
        for (PriceMedicament priceMedicament: priceMedicaments) {
            priceMedicamentResponse =  mapPriceMedicamentToPriceMedicamentResponse(priceMedicament);
            priceMedicamentResponses.add(priceMedicamentResponse);
        }
        return priceMedicamentResponses;
    }


    private PriceMedicamentResponse mapPriceMedicamentToPriceMedicamentResponse(PriceMedicament priceMedicament){
        PriceMedicamentResponse priceMedicamentResponse = new PriceMedicamentResponse();
        priceMedicamentResponse.setId(priceMedicament.getId());
        priceMedicamentResponse.setPrice(priceMedicament.getPrice());
        priceMedicamentResponse.setPharmacyMedicamentResponse(_pharmacyMedicamentService.mapPharmacyMedicamentToPharmacyMedicamentResponse(priceMedicament.getPharmacyMedicament()));
        priceMedicamentResponse.setPricelistResponse(_pricelistService.mapPricelistToPricelistResponse(priceMedicament.getPricelist()));
        return priceMedicamentResponse;
    }
}
