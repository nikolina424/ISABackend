package com.example.isabackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPharmacistResponse {
    private List<PharmacistResponse> pharmacistResponses;
}
