package com.example.VaccinationBookingSystem.repository;

import com.example.VaccinationBookingSystem.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query(value = "Select * from doctor d where d.vaccination_centre_id = :vaccinationCentre", nativeQuery = true)
    List<Doctor> findAllDoctorOfCenter(int vaccinationCentre);


}
