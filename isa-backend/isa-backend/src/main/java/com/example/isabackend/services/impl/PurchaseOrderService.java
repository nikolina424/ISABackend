package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.CreatePurchaseOrderRequest;
import com.example.isabackend.dto.response.PharmacistResponse;
import com.example.isabackend.dto.response.PurchaseOrderResponse;
import com.example.isabackend.entity.Pharmacist;
import com.example.isabackend.entity.PurchaseOrder;
import com.example.isabackend.repository.IPharmacistRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.repository.IPurchaseOrderRepository;
import com.example.isabackend.services.IPurchaseOrderService;
import com.example.isabackend.util.enums.PurchaseOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {
    private final IPurchaseOrderRepository _poRepository;
    private final PharmacyService _pharmacyService;
    private final IPharmacyRepository _pharmacyRepository;
    private final PharmacistService _pharmacistService;
    private final IPharmacistRepository _pharmacistRepository;


    public PurchaseOrderService(IPurchaseOrderRepository poRepository, PharmacyService pharmacyService, IPharmacyRepository pharmacyRepository, PharmacistService pharmacistService, IPharmacistRepository pharmacistRepository) {
        _poRepository = poRepository;
        _pharmacyService = pharmacyService;
        _pharmacyRepository = pharmacyRepository;
        _pharmacistService = pharmacistService;
        _pharmacistRepository = pharmacistRepository;
    }

    public PurchaseOrderResponse mapPurchaseOrderToPurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        PurchaseOrderResponse purchaseOrderResponse = new PurchaseOrderResponse();
        purchaseOrderResponse.setId(purchaseOrder.getId());
        purchaseOrderResponse.setLimitDate(purchaseOrder.getLimitDate());
        purchaseOrderResponse.setPharmacy(_pharmacyService.mapPharmacyToPharmacyResponse(purchaseOrder.getPharmacy()));
        purchaseOrderResponse.setPharmacist(_pharmacistService.mapPharmacistToPharmacistResponse(purchaseOrder.getPharmacist()));
        purchaseOrderResponse.setPurchaseOrderStatus(purchaseOrder.getPurchaseOrderStatus());
        if(purchaseOrder.getLimitDate().isAfter(LocalDate.now())){
            purchaseOrderResponse.setActive(true);
        }else{
            purchaseOrderResponse.setActive(false);
        }
        return purchaseOrderResponse;
    }

    @Override
    public PurchaseOrderResponse createPurchaseOrder(CreatePurchaseOrderRequest request) {
        LocalDate limitDate = LocalDate.parse(request.getLimitDate());
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setLimitDate(limitDate);
        purchaseOrder.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));
        purchaseOrder.setPharmacist(_pharmacistRepository.findOneById(request.getPharmacistId()));
        purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.ACTIVE);
        if(limitDate.isAfter(LocalDate.now())){
            purchaseOrder.setActive(true);
        }else{
            purchaseOrder.setActive(false);
        }
        _poRepository.save(purchaseOrder);
        return mapPurchaseOrderToPurchaseOrderResponse(purchaseOrder);
    }

    @Override
    public List<PurchaseOrderResponse> getAllByPharmacyId(Long id) {
        List<PurchaseOrder> purchaseOrders = _poRepository.findAll();
        List<PurchaseOrderResponse> purchaseOrderResponses = new ArrayList<>();
        for (PurchaseOrder purchaseOrder: purchaseOrders) {
            if(purchaseOrder.getPharmacy().getId().equals(id)){
                purchaseOrderResponses.add(mapPurchaseOrderToPurchaseOrderResponse(purchaseOrder));
            }
        }
        return purchaseOrderResponses;
    }
}
