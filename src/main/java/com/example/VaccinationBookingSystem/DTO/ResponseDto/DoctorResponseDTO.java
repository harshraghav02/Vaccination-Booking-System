package com.example.VaccinationBookingSystem.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDTO {
    String name;

    String emailId;

    String contactNo;

    VaccinationCentreResponseDTO vaccinationCentreResponseDTO;
}
