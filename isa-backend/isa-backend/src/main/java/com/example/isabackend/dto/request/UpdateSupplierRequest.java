package com.example.isabackend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String number;
}
