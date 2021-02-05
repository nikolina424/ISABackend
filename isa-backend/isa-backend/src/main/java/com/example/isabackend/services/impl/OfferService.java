package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.ChangeOfferRequest;
import com.example.isabackend.dto.request.CreateOfferRequest;
import com.example.isabackend.dto.response.OfferResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IOfferRepository;
import com.example.isabackend.repository.IPurchaseOrderRepository;
import com.example.isabackend.repository.ISupplierRepository;
import com.example.isabackend.services.IOfferService;
import com.example.isabackend.util.enums.OfferStatus;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfferService implements IOfferService {

    private final IOfferRepository _offerRepository;
    private final SupplierService _supplierService;
    private final PurchaseOrderService _poService;
    private final ISupplierRepository _supplierRepository;
    private final IPurchaseOrderRepository _poRepository;

    public OfferService(IOfferRepository offerRepository, SupplierService supplierService, PurchaseOrderService poService, ISupplierRepository supplierRepository, IPurchaseOrderRepository poRepository) {
        _offerRepository = offerRepository;
        _supplierService = supplierService;
        _poService = poService;
        _supplierRepository = supplierRepository;
        _poRepository = poRepository;
    }

    @Override
    public List<OfferResponse> getAllOffersBySupplierId(Long id) {
        List<Offer> allOffers = _offerRepository.findAll();
        List<OfferResponse> supplierOffers = new ArrayList<>();
        for (Offer offer: allOffers) {
            if(offer.getSupplier().getId().equals(id)){
                supplierOffers.add(mapOfferToOfferResponse(offer));
            }
        }
        return supplierOffers;
    }

    @Override
    public OfferResponse createOffer(CreateOfferRequest request) {

        Supplier supplier = _supplierRepository.findOneById(request.getSupplierId());
        List<SupplierMedicament> supplierMedicaments = supplier.getSupplierMedicaments();
        PurchaseOrder purchaseOrder = _poRepository.findOneById(request.getPurchaseOrderId());
        List<OrderMedicament> orderMedicaments = purchaseOrder.getOrderMedicaments();

        Offer offer = new Offer();
        LocalDate deliveryDate = LocalDate.parse(request.getDeliveryDate());

        for(OrderMedicament orderMedicament: orderMedicaments){
            int counter = 0;
            for(SupplierMedicament supplierMedicament: supplierMedicaments){
                if(orderMedicament.getMedicament().getId() == supplierMedicament.getMedicament().getId()){
                    if(orderMedicament.getQuantity() > supplierMedicament.getQuantity()){
                        System.out.println("nema me u dovoljnim kolicinama");
                        return null;
                    }
                }else{
                    counter++;
                }

                if(counter == supplierMedicaments.size()){
                    System.out.println("nema me");
                    return null;
                }
            }
        }

        offer.setDeliveryDate(deliveryDate);
        offer.setPrice(request.getPrice());
        offer.setPurchaseOrder(purchaseOrder);
        offer.setSupplier(supplier);
        offer.setOfferStatus(OfferStatus.PENDING);
        Offer savedOffer = _offerRepository.save(offer);

        return mapOfferToOfferResponse(savedOffer);

    }

    @Override
    public void changeOffer(ChangeOfferRequest request) {
        Offer offer = _offerRepository.findOneById(request.getOfferId());
        offer.setPrice(request.getPrice());
        LocalDate deliveryDate = LocalDate.parse(request.getDeliveryDate());
        offer.setDeliveryDate(deliveryDate);
        _offerRepository.save(offer);
    }

    @Override
    public OfferResponse getOffer(Long id) {
        Offer offer = _offerRepository.findOneById(id);
        if(offer != null) {
            return mapOfferToOfferResponse(offer);
        } else {
            return null;
        }
    }

    @Override
    public List<OfferResponse> getAllOffersByPurchaseOrderId(Long id) {
        List<Offer> allOffers = _offerRepository.findAll();
        List<OfferResponse> supplierOffers = new ArrayList<>();
        for (Offer offer: allOffers) {
            if(offer.getPurchaseOrder().getId().equals(id)){
                supplierOffers.add(mapOfferToOfferResponse(offer));
            }
        }
        return supplierOffers;
    }


    public OfferResponse mapOfferToOfferResponse(Offer offer) {
        OfferResponse offerResponse = new OfferResponse();
        offerResponse.setId(offer.getId());
        offerResponse.setDeliveryDate(offer.getDeliveryDate());
        offerResponse.setPurchaseOrder(_poService.mapPurchaseOrderToPurchaseOrderResponse(offer.getPurchaseOrder()));
        offerResponse.setSupplier(_supplierService.mapSupplierToSupplierResponse(offer.getSupplier()) );
        offerResponse.setPrice(offer.getPrice());
        offerResponse.setOfferStatus(offer.getOfferStatus());
        return offerResponse;
    }
}
