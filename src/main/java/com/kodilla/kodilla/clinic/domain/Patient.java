package com.kodilla.kodilla.clinic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private int peselId;

    @Column
    private String medicalHistory;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "patients")
    private List<Doctor> doctors;
}
