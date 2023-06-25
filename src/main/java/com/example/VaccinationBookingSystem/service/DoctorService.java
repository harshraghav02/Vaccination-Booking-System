package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.DTO.RequestDto.DoctorRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.DoctorResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;

import java.util.List;

public interface DoctorService {
    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO) throws CenterNotPresentException;

    List<String> getAllDoctorOfCenter(int vaccinationCentre);
}
