package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.DTO.RequestDto.VaccinationCentreRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.Transfomer.VaccinationCentreTransformer;
import com.example.VaccinationBookingSystem.repository.DoctorRepository;
import com.example.VaccinationBookingSystem.repository.VaccinationCentreRepository;
import com.example.VaccinationBookingSystem.service.VaccinationCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCentreServiceImpl implements VaccinationCentreService {

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public VaccinationCentreResponseDTO addCentre(VaccinationCentreRequestDTO vaccinationCentreRequestDTO) {

        // dto -> entity
        VaccinationCentre vaccinationCentre = VaccinationCentreTransformer.vaccinationCenterRequestDTOtoEntity(vaccinationCentreRequestDTO);

        // save entity into  db
        VaccinationCentre savedCenter = vaccinationCentreRepository.save(vaccinationCentre);

        // entity to response dto

        return VaccinationCentreTransformer.centerToResponseDTO(savedCenter);
    }

    @Override
    public String deleteCenter(int vaccinationCenterId) throws CenterNotPresentException {

        // check center
        Optional<VaccinationCentre> optionalVaccinationCentre = vaccinationCentreRepository.findById(vaccinationCenterId);
        if(!optionalVaccinationCentre.isPresent()){
            throw new CenterNotPresentException("Vaccination center Not Found!");

        }

        VaccinationCentre vaccinationCentre = optionalVaccinationCentre.get();

        // Delete doctor who work in this Center

        List<Doctor> doctorList = doctorRepository.findAllDoctorOfCenter(vaccinationCenterId);
        for(Doctor doctor : doctorList){
            doctorRepository.delete(doctor);
        }

        vaccinationCentreRepository.delete(vaccinationCentre);

        return "Vaccination Center Deleted SuccessFully";
    }

    @Override
    public VaccinationCentre getCenterWithMostAppointments() throws CenterNotPresentException {
        if(vaccinationCentreRepository.findAll().size() == 0){
            throw new CenterNotPresentException("No Vaccination Center Present");
        }

        List<VaccinationCentre> vaccinationCentreList = vaccinationCentreRepository.findAll();

        VaccinationCentre vaccinationCentreWithMostAppointments = null;
        int numberOfAppointments = -1;

        for(VaccinationCentre vaccinationCentre : vaccinationCentreList){
            if(numberOfAppointments < vaccinationCentre.getAppointments().size()){
                numberOfAppointments = vaccinationCentre.getAppointments().size();
                vaccinationCentreWithMostAppointments = vaccinationCentre;
            }
        }

        return vaccinationCentreWithMostAppointments;
    }
}
