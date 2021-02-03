package com.example.isabackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String number;
    private String address;
    private String userRole;
}
