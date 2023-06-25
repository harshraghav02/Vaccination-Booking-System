package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.DTO.RequestDto.DoctorRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.DoctorResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.service.DoctorService;
import com.example.VaccinationBookingSystem.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorServiceImpl doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO){
        try{
            DoctorResponseDTO doctorResponseDTO = doctorService.addDoctor(doctorRequestDTO);
            return new ResponseEntity<>(doctorResponseDTO,HttpStatus.CREATED);
        } catch (CenterNotPresentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-all-doctor/{vaccinationCenter}")
    public ResponseEntity getAllDoctorOfVaccinationCenter(@PathVariable("vaccinationCenter")int  vaccinationCentre){
        List<String> doctorList = doctorService.getAllDoctorOfCenter(vaccinationCentre);
        return new ResponseEntity(doctorList,HttpStatus.FOUND);
    }
}
