package com.example.VaccinationBookingSystem.DTO.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.DoseNo;
import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDTO {

    String userName;

    String appointmentNo;

    Date dateOfAppointment;

    DoseNo doseNo;

    VaccinationCentreResponseDTO vaccinationCentreResponseDTO;

    String doctorName;

    VaccinationType vaccinationType;
}
