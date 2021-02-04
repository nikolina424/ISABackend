package com.example.isabackend.dto.response;

import com.example.isabackend.entity.Pricelist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacistResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String number;
    private String address;
    private float rating;
    private double price;
    private PricelistResponse pricelistResponse;

}
