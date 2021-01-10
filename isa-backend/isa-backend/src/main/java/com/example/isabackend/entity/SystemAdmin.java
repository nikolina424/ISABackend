package com.example.isabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemAdmin {

    private Long id;

    private User user;

    private String firstName;

    private String lastName;
}
