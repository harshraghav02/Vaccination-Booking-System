package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.DTO.RequestDto.DoctorRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.DoctorResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.Transfomer.DoctorTransformer;
import com.example.VaccinationBookingSystem.repository.DoctorRepository;
import com.example.VaccinationBookingSystem.repository.VaccinationCentreRepository;
import com.example.VaccinationBookingSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;
    @Override
    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO) throws CenterNotPresentException {
        // check if center is present or not

        Optional<VaccinationCentre> optionalVaccinationCentre =  vaccinationCentreRepository.findById(doctorRequestDTO.getCenterId());

        if(!optionalVaccinationCentre.isPresent()){
            throw new CenterNotPresentException("Center not exist!!!");
        }

        VaccinationCentre center = optionalVaccinationCentre.get();

        // dto to entity
        Doctor doctor = DoctorTransformer.doctorRequestDTOtoDoctor(doctorRequestDTO);
        doctor.setVaccinationCentre(center);
        center.getDoctors().add(doctor);

        // save to doctor repository and center repository
        Doctor savedDoctor  = doctorRepository.save(doctor);

        VaccinationCentre savedCenter = vaccinationCentreRepository.save(center);


        // entity to response dto
        DoctorResponseDTO doctorResponseDTO = DoctorTransformer.doctorToDoctorResponseDTO(doctor);
        return doctorResponseDTO;
    }

    @Override
    public List<String> getAllDoctorOfCenter(int vaccinationCentre) {

        List<Doctor> list = doctorRepository.findAllDoctorOfCenter(vaccinationCentre);

        List<String> doctorList = new ArrayList<>();

        for(Doctor doctor : list){
            doctorList.add(doctor.getName());
        }

        return doctorList;
    }
}
