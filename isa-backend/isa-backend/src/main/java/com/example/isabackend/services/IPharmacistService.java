package com.example.isabackend.services;

import com.example.isabackend.dto.response.SearchPharmacistResponse;

public interface IPharmacistService {
    SearchPharmacistResponse searchPharmacist(String firstName, String lastName);
}
