package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import com.example.VaccinationBookingSystem.Model.Dose2;
import com.example.VaccinationBookingSystem.Model.User;

public interface Dose2Service {
    public Dose2 createDose2(User user, VaccinationType vaccinationType);
}
