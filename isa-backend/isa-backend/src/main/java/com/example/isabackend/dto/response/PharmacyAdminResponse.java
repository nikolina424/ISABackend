package com.example.isabackend.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyAdminResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Long pharmacyId;
}
