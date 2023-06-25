package com.example.VaccinationBookingSystem.repository;

import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCentreRepository extends JpaRepository<VaccinationCentre,Integer> {


}
