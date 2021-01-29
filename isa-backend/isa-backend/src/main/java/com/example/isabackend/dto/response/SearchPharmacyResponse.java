package com.example.isabackend.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  SearchPharmacyResponse {
    private List<PharmacyResponse> pharmacyResponses;
}
