package com.example.isabackend.dto.response;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicamentResponse {
    private Long id;
    private String name;
    private String type;
    private String contraindications;
    private String ingredients;
    private String code;
    private String shape;
    private String replacement;
    private String manufacturer;
    private String issuance;
    private String notes;
    private float rating;

}
