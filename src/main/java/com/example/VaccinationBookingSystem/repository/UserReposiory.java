package com.example.VaccinationBookingSystem.repository;

import com.example.VaccinationBookingSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReposiory extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * from user u where u.name = :name",nativeQuery = true)
    User findUserByName(String name);

    @Query(value = "Select * from user u where u.is_dose1_taken = 1", nativeQuery = true)
    List<User> findAllUserTakenDose1();

    @Query(value = "Select * from user u where u.is_dose2_taken = 1", nativeQuery = true)
    List<User> findAllUserTakenDose2();
}
