package com.example.VaccinationBookingSystem.Transfomer;

import com.example.VaccinationBookingSystem.DTO.ResponseDto.AppointmentResponseDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import com.example.VaccinationBookingSystem.Model.Appointment;

public class AppintmentTransformer {
    public static AppointmentResponseDTO appointmentToappointmentResponseDTO(Appointment appointment, VaccinationType vaccinationType){
        return AppointmentResponseDTO.builder()
                .appointmentNo(appointment.getAppintmentNo())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .userName(appointment.getUser().getName())
                .doctorName(appointment.getDoctor().getName())
                .doseNo(appointment.getDoseNo())
                .vaccinationType(vaccinationType)
                .vaccinationCentreResponseDTO(VaccinationCentreTransformer.centerToResponseDTO(appointment.getDoctor().getVaccinationCentre()))
                .build();
    }
}
