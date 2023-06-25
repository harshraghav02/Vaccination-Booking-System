package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.DTO.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Exception.IncorrectContactNumber;
import com.example.VaccinationBookingSystem.Model.User;

import java.util.List;

public  interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws IncorrectContactNumber;

    public List<String> getAllUser();

    List<String> getAllUserTakenDose1();

    List<String> getAllUserTakenDose2();
}
