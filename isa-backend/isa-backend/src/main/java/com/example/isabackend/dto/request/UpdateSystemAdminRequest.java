package com.example.isabackend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSystemAdminRequest {
    private String username;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
}
