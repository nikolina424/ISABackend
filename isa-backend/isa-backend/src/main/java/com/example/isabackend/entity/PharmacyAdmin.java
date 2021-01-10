package com.example.isabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyAdmin {

    private Long id;

    private User user;

    private String firstName;

    private String lastName;
}
