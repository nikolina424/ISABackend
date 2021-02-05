package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.response.OrderMedicamentResponse;
import com.example.isabackend.dto.response.PurchaseOrderResponse;
import com.example.isabackend.entity.OrderMedicament;
import com.example.isabackend.entity.PurchaseOrder;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.repository.IOrderMedicamentRepository;
import com.example.isabackend.repository.IPurchaseOrderRepository;
import com.example.isabackend.services.IOrderMedicamentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderMedicamentService implements IOrderMedicamentService {
    private final IOrderMedicamentRepository _omRepository;
    private final IMedicamentRepository _medicamentRepository;
    private final IPurchaseOrderRepository _poRepository;
    private final MedicamentService _medicamentService;
    private final PurchaseOrderService _poService;

    public OrderMedicamentService(IOrderMedicamentRepository omRepository, IMedicamentRepository medicamentRepository, IPurchaseOrderRepository poRepository, MedicamentService medicamentService, PurchaseOrderService poService) {
        _omRepository = omRepository;
        _medicamentRepository = medicamentRepository;
        _poRepository = poRepository;
        _medicamentService = medicamentService;
        _poService = poService;
    }

    @Override
    public OrderMedicamentResponse createOrderMedicament(CreateOrderMedicamentRequest request) {
        OrderMedicament orderMedicament = new OrderMedicament();
        orderMedicament.setMedicament(_medicamentRepository.findOneById(request.getMedicamentId()));
        orderMedicament.setPurchaseOrder(_poRepository.findOneById(request.getPurchaseOrderId()));
        orderMedicament.setQuantity(request.getQuantity());
        _omRepository.save(orderMedicament);
        return mapOrderMedicamentToOrderMedicamentResponse(orderMedicament);
    }

    @Override
    public List<OrderMedicamentResponse> getAllByOrderId(Long id) {
        List<OrderMedicament> orderMedicaments = _omRepository.findAll();
        List<OrderMedicamentResponse> orderMedicamentResponses = new ArrayList<>();
        for (OrderMedicament orderMedicament: orderMedicaments) {
            if(orderMedicament.getPurchaseOrder().getId().equals(id)){
                orderMedicamentResponses.add(mapOrderMedicamentToOrderMedicamentResponse(orderMedicament));
            }
        }
        return orderMedicamentResponses;    }

    private OrderMedicamentResponse mapOrderMedicamentToOrderMedicamentResponse(OrderMedicament orderMedicament) {
        OrderMedicamentResponse orderMedicamentResponse = new OrderMedicamentResponse();
        orderMedicamentResponse.setId(orderMedicament.getId());
        orderMedicamentResponse.setQuantity(orderMedicament.getQuantity());
        orderMedicamentResponse.setMedicament(_medicamentService.mapMedicamentToMedicamentResponse(orderMedicament.getMedicament()));
        orderMedicamentResponse.setPurchaseOrder(_poService.mapPurchaseOrderToPurchaseOrderResponse(orderMedicament.getPurchaseOrder()));
        return orderMedicamentResponse;
    }
}
