package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.DTO.RequestDto.VaccinationCentreRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;

public interface VaccinationCentreService {
    public VaccinationCentreResponseDTO addCentre(VaccinationCentreRequestDTO vaccinationCentreRequestDTO);

    String deleteCenter(int vaccinationCenterId) throws CenterNotPresentException;

    VaccinationCentre getCenterWithMostAppointments() throws CenterNotPresentException;
}
