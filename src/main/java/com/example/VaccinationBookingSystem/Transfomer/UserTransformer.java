package com.example.VaccinationBookingSystem.Transfomer;

import com.example.VaccinationBookingSystem.DTO.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Model.User;

public class UserTransformer {
    public static User UserRequestDTOtoUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .contactNo(userRequestDto.getContactNo())
                .emailId(userRequestDto.getEmailId())
                .gender(userRequestDto.getGender())
                .build();
    }

    public static UserResponseDto UserToUseResponseDTO(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats!! You are Registered Successfully!")
                .build();
    }
}
