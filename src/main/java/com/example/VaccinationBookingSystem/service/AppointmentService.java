package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.DTO.RequestDto.AppointmentRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.AppointmentResponseDTO;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFound;
import com.example.VaccinationBookingSystem.Exception.DoseAlreadytaken;
import com.example.VaccinationBookingSystem.Exception.NotElegibleForDose2;
import com.example.VaccinationBookingSystem.Exception.UserNotFound;

public interface AppointmentService {
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentRequestDTO) throws UserNotFound, DoctorNotFound, NotElegibleForDose2, DoseAlreadytaken;
}
