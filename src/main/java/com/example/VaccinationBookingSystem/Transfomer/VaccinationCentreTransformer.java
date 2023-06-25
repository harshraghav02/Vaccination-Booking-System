package com.example.VaccinationBookingSystem.Transfomer;

import com.example.VaccinationBookingSystem.DTO.RequestDto.VaccinationCentreRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;

public class VaccinationCentreTransformer {

    public static VaccinationCentre vaccinationCenterRequestDTOtoEntity(VaccinationCentreRequestDTO vaccinationCentreRequestDTO){
        return VaccinationCentre.builder()
        .name(vaccinationCentreRequestDTO.getName())
        .location(vaccinationCentreRequestDTO.getLocation())
        .centreType(vaccinationCentreRequestDTO.getCentreType())
        .build();
    }

    public static VaccinationCentreResponseDTO centerToResponseDTO(VaccinationCentre vaccinationCentre){

        return VaccinationCentreResponseDTO.builder()
                .name(vaccinationCentre.getName())
                .location(vaccinationCentre.getLocation())
                .centreType(vaccinationCentre.getCentreType())
                .build();
    }
}
