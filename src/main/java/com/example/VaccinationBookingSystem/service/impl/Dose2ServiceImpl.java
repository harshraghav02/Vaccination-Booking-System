package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import com.example.VaccinationBookingSystem.Model.Dose2;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.service.Dose2Service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class Dose2ServiceImpl implements Dose2Service {
    @Override
    public Dose2 createDose2(User user, VaccinationType vaccinationType) {
        Dose2 dose2 = Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccinationType(vaccinationType)
                .user(user)
                .build();
        return dose2;
    }
}
