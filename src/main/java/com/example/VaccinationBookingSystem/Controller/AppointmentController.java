package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.DTO.RequestDto.AppointmentRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.AppointmentResponseDTO;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFound;
import com.example.VaccinationBookingSystem.Exception.DoseAlreadytaken;
import com.example.VaccinationBookingSystem.Exception.NotElegibleForDose2;
import com.example.VaccinationBookingSystem.Exception.UserNotFound;
import com.example.VaccinationBookingSystem.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentServiceImpl appointmentService;
    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) throws DoctorNotFound, UserNotFound, NotElegibleForDose2, DoseAlreadytaken {
        try{
            AppointmentResponseDTO appointmentResponseDTO = appointmentService.bookAppointment(appointmentRequestDTO);
            return new ResponseEntity(appointmentResponseDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


}
