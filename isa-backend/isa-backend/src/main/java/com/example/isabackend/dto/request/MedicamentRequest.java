package com.example.isabackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentRequest {

    private String name;
    private String code;
    private String shape;
    private String type;
    private String replacement;
    private String contraindications;
    private String ingredients;
    private String manufacturer;
    private String issuance;
    private String notes;
}
