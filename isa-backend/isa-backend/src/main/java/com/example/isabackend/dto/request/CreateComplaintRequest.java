package com.example.isabackend.dto.request;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateComplaintRequest {
    private String text;
    private Long complainingObjectId;
    private Long pharmacyId;
    private Long patientId;
}
