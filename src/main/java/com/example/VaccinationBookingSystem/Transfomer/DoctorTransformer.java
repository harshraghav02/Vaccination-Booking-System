package com.example.VaccinationBookingSystem.Transfomer;

import com.example.VaccinationBookingSystem.DTO.RequestDto.DoctorRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.DoctorResponseDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;

public class DoctorTransformer {
    public static Doctor doctorRequestDTOtoDoctor(DoctorRequestDTO doctorRequestDTO){
        return Doctor.builder()
                .name(doctorRequestDTO.getName())
                .age(doctorRequestDTO.getAge())
                .contactNo(doctorRequestDTO.getContactNo())
                .emailId(doctorRequestDTO.getEmailId())
                .gender(doctorRequestDTO.getGender())
                .build();
    }

    public static DoctorResponseDTO doctorToDoctorResponseDTO(Doctor doctor){

        // prepare response dto
        VaccinationCentreResponseDTO vaccinationCentreResponseDTO = VaccinationCentreTransformer.centerToResponseDTO(doctor.getVaccinationCentre());
        return DoctorResponseDTO.builder()
                .name(doctor.getName())
                .contactNo(doctor.getContactNo())
                .emailId(doctor.getEmailId())
                .vaccinationCentreResponseDTO(vaccinationCentreResponseDTO).build();
    }
}
