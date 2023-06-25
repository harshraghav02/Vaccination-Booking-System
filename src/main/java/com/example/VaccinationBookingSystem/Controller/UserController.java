package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.DTO.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Exception.IncorrectContactNumber;
import com.example.VaccinationBookingSystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto) throws IncorrectContactNumber {
        UserResponseDto savedUser = userService.addUser(userRequestDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-user")
    public ResponseEntity getAllUser(){
        List<String> listOfUserName = userService.getAllUser();
        return new ResponseEntity(listOfUserName,HttpStatus.FOUND);

    }

    @GetMapping("/get-all-user-taken-dose1")
    public ResponseEntity getAllUserTakenDose1(){
        List<String> list = userService.getAllUserTakenDose1();
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/get-all-user-taken-dose2")
    public ResponseEntity getAllUserTakenDose2(){
        List<String> list = userService.getAllUserTakenDose2();
        return new ResponseEntity(list,HttpStatus.FOUND);
    }


}
