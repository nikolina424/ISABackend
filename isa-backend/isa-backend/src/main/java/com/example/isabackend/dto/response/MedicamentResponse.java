package com.example.isabackend.dto.response;

import lombok.*;

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
}
