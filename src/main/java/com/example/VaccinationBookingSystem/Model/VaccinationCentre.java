package com.example.VaccinationBookingSystem.Model;

import com.example.VaccinationBookingSystem.Enum.CentreType;
import com.example.VaccinationBookingSystem.Model.Appointment;
import com.example.VaccinationBookingSystem.Model.Doctor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "vaccination_centre")
@Builder
public class VaccinationCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "location")
    String location;

    @Column(name = "center_type")
    @Enumerated(EnumType.STRING)
    CentreType centreType;

    @OneToMany(mappedBy = "vaccinationCentre",cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "vaccinationCentre",cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();


}
