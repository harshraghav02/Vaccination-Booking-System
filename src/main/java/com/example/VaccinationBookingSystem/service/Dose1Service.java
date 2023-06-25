package com.example.VaccinationBookingSystem.service;

import com.example.VaccinationBookingSystem.Enum.VaccinationType;
import com.example.VaccinationBookingSystem.Model.Dose1;
import com.example.VaccinationBookingSystem.Model.User;

public interface Dose1Service {
    public Dose1 createDose1(User user, VaccinationType vaccinationType);
}
