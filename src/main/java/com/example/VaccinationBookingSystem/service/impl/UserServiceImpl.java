package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.DTO.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Exception.IncorrectContactNumber;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Transfomer.UserTransformer;
import com.example.VaccinationBookingSystem.repository.UserReposiory;
import com.example.VaccinationBookingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    UserReposiory userReposiory;
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws IncorrectContactNumber {
        // dto -> entity
//        User user = new User();
//        user.setName(userRequestDto.getName());
//        user.setAge(userRequestDto.getAge());
//        user.setContactNo(userRequestDto.getContactNo());
//        user.setEmailId(userRequestDto.getEmailId());
//        user.setGender(userRequestDto.getGender());

        //check contact number
        if(userRequestDto.getContactNo().length() != 10)
            throw new IncorrectContactNumber("Contact Number Should be of 10 digit");
        for(char c :userRequestDto.getContactNo().toCharArray()){
            if(c<48 || c>57)
                throw new IncorrectContactNumber("Contact Number Incorrect");
        }

        // Object Creation Using Builder

        User user = UserTransformer.UserRequestDTOtoUser(userRequestDto);

        User savedUser = userReposiory.save(user);

        // entity -> dto

//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(savedUser.getName());
//        userResponseDto.setMessage("Congrats!! You are Registered Successfully!");

        UserResponseDto userResponseDto = UserTransformer.UserToUseResponseDTO(savedUser);

        // send mail

        String text = "Congrats!! " + user.getName() + " you are successfully registered. " +"\n" + "\n"
                +"User Name : " + user.getName() + "\n" + "\n"
                +"Now you can Book your Appointment for Dose 1";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("puublicstaticvoidmain@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("User Added Successfully!");
        message.setText(text);
        javaMailSender.send(message);

        return userResponseDto;

    }

    public List<String> getAllUser() {

        List<User> users = userReposiory.findAll();

        List<String> listOfName = new ArrayList<>();

        for(User user :users){
            listOfName.add(user.getName());
        }

        return listOfName;
    }

    @Override
    public List<String> getAllUserTakenDose1() {

        List<User> users = userReposiory.findAllUserTakenDose1();

        List<String> listOfName = new ArrayList<>();

        for(User user :users){
            listOfName.add(user.getName());
        }

        return listOfName;
    }

    @Override
    public List<String> getAllUserTakenDose2() {
        List<User> users = userReposiory.findAllUserTakenDose2();

        List<String> listOfName = new ArrayList<>();

        for(User user :users){
            listOfName.add(user.getName());
        }

        return listOfName;
    }
}
