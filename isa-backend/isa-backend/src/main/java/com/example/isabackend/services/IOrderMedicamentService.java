package com.example.isabackend.services;

import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.response.OrderMedicamentResponse;

public interface IOrderMedicamentService {
    OrderMedicamentResponse createOrderMedicament(CreateOrderMedicamentRequest request);
}
