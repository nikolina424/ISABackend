package com.example.isabackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DermatologistResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String number;
    private String address;
    private List<PharmacyResponse> pharmacyResponses;
    private float rating;


}
