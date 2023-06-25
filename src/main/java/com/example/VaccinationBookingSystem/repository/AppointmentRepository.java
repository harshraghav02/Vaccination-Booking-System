package com.example.VaccinationBookingSystem.repository;

import com.example.VaccinationBookingSystem.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
