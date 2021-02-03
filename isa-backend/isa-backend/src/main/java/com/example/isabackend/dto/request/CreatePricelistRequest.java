package com.example.isabackend.dto.request;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePricelistRequest {
    private String fromDate;
    private String toDate;
    private Long pharmacyId;

}
