package com.example.isabackend.dto.request;

import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemoveFromPharmacyRequest {
    private Long pharmacyId;
    private Long itemId;
}
