package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.DTO.RequestDto.VaccinationCentreRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.VaccinationCentreResponseDTO;
import com.example.VaccinationBookingSystem.Exception.CenterNotPresentException;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import com.example.VaccinationBookingSystem.service.impl.VaccinationCentreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/centre")

public class VaccinationCentreController {

    @Autowired
    VaccinationCentreServiceImpl vaccinationCentreService;
    @PostMapping("/add")
    public ResponseEntity addCentre(@RequestBody VaccinationCentreRequestDTO vaccinationCentreRequestDTO){
        VaccinationCentreResponseDTO vaccinationCentreResponseDTO = vaccinationCentreService.addCentre(vaccinationCentreRequestDTO);

        return new ResponseEntity(vaccinationCentreResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-center")
    public ResponseEntity deleteCenter(@RequestParam int vaccinationCenterId){
        try{
            String message = vaccinationCentreService.deleteCenter(vaccinationCenterId);
            return new ResponseEntity(message,HttpStatus.ACCEPTED);
        } catch (CenterNotPresentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/center/most/appointments")
    public ResponseEntity getCenterWithMostAppointments(){
        try{
            VaccinationCentre vaccinationCentre = vaccinationCentreService.getCenterWithMostAppointments();
            return new ResponseEntity(vaccinationCentre,HttpStatus.FOUND);
        }
        catch (CenterNotPresentException c){
            return new ResponseEntity(c.getMessage(),HttpStatus.EXPECTATION_FAILED);

        }
    }
}
