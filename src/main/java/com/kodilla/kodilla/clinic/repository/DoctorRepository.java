package com.kodilla.kodilla.clinic.repository;

import com.kodilla.kodilla.clinic.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    List<Doctor> findAll();
}
