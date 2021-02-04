package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.CreatePricelistRequest;
import com.example.isabackend.dto.response.PricelistResponse;
import com.example.isabackend.entity.Pharmacy;
import com.example.isabackend.entity.PriceMedicament;
import com.example.isabackend.entity.Pricelist;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.repository.IPricelistRepository;
import com.example.isabackend.services.IPricelistService;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistService implements IPricelistService {
    private final IPricelistRepository _pricelistRepository;
    private final PharmacyService _pharmacyService;
    private final IPharmacyRepository _pharmacyRepository;

    public PricelistService(IPricelistRepository pricelistRepository, PharmacyService pharmacyService, IPharmacyRepository pharmacyRepository) {
        _pricelistRepository = pricelistRepository;
        _pharmacyService = pharmacyService;
        _pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<PricelistResponse> getAllByPharmacyId(Long id) {
        List<Pricelist> pricelists = _pricelistRepository.getAllByPharmacyId(id);
        return mapPricelistsToPricelistResponses(pricelists);
    }

    @Override
    public PricelistResponse getActivePricelistByPharmacyId(Long id) {
       List<Pricelist> allPricelists = _pricelistRepository.getAllByPharmacyId(id);
       Pricelist pricelist = new Pricelist();
       for(Pricelist p: allPricelists){
           if(p.getActive()){
                pricelist = p;
           }
       }
       return mapPricelistToPricelistResponse(pricelist);
    }

    @Override
    public PricelistResponse createPricelist(CreatePricelistRequest request) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        LocalDate fromDate = LocalDate.parse(request.getFromDate());
        LocalDate toDate = LocalDate.parse(request.getToDate());

        List<Pricelist> pricelists = _pricelistRepository.getAllByPharmacyId(pharmacy.getId());
        for(Pricelist p: pricelists){
            if(p.getActive()){
                p.setActive(false);
                _pricelistRepository.save(p);
            }
        }


        Pricelist pricelist = new Pricelist();
        pricelist.setActive(true);
        pricelist.setFromDate(fromDate);
        pricelist.setToDate(toDate);
        pricelist.setPharmacy(pharmacy);
        _pricelistRepository.save(pricelist);



        return mapPricelistToPricelistResponse(pricelist);
    }

    private List<PricelistResponse> mapPricelistsToPricelistResponses(List<Pricelist> pricelists) {
       PricelistResponse pricelistResponse = new PricelistResponse();
       List<PricelistResponse> pricelistResponses = new ArrayList<>();
        for (Pricelist pricelist: pricelists) {
           pricelistResponse =  mapPricelistToPricelistResponse(pricelist);
           pricelistResponses.add(pricelistResponse);
        }
        return pricelistResponses;
    }


    public PricelistResponse mapPricelistToPricelistResponse(Pricelist pricelist){
        PricelistResponse pricelistResponse = new PricelistResponse();
        pricelistResponse.setId(pricelist.getId());
        pricelistResponse.setActive(pricelist.getActive());
        pricelistResponse.setFromDate(pricelist.getFromDate());
        pricelistResponse.setToDate(pricelist.getToDate());
        pricelistResponse.setPharmacyResponse(_pharmacyService.mapPharmacyToPharmacyResponse(pricelist.getPharmacy()));
        return pricelistResponse;
    }
}
