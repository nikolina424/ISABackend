package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.response.OrderMedicamentResponse;

import java.util.List;

public interface IOrderMedicamentService {
    OrderMedicamentResponse createOrderMedicament(CreateOrderMedicamentRequest request);

    List<OrderMedicamentResponse> getAllByOrderId(Long id);
}
