package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatientRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String number;

}
