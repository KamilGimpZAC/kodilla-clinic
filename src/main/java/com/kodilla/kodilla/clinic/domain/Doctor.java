package com.kodilla.kodilla.clinic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String specialization;

    @Column
    private int price;

    @Column
    private int rating;

    @Column
    private String dayOfWeek;

    @Column
    private LocalDateTime officeHoursStart;

    @Column
    private LocalDateTime officeHoursEnd;

    @Column
    private boolean free;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Doctor_Has_Patient",
            joinColumns = {@JoinColumn(name = "DoctorId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "PatientId", referencedColumnName = "id")}
    )
    private List<Patient> patients;
}
