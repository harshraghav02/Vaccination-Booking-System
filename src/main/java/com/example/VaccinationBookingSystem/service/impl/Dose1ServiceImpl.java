package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import com.example.VaccinationBookingSystem.Model.Dose1;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.service.Dose1Service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class Dose1ServiceImpl implements Dose1Service {
    @Override
    public Dose1 createDose1(User user, VaccinationType vaccinationType) {
        Dose1 dose1 = Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccinationType(vaccinationType)
                .user(user)
                .build();
        return dose1;
    }
}
