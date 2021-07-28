package com.kodilla.kodilla.clinic.domain;

import com.kodilla.kodilla.clinic.exception.DoctorException;
import com.kodilla.kodilla.clinic.repository.DoctorRepository;
import com.kodilla.kodilla.clinic.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorTestSuite {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testCreate(){
        //Given
        Doctor doctor = new Doctor();
        //When
        doctorRepository.save(doctor);
        //Then
        assertEquals(1,doctorRepository.count());
        //Cleanup
        try {
            doctorRepository.deleteAll();
        }catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete(){
        //Given
        Doctor doctor = new Doctor();
        //When&Then
        doctorRepository.save(doctor);
        assertEquals(1,doctorRepository.count());
        doctorRepository.deleteById(doctor.getId());
        assertEquals(0,doctorRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Doctor doctor = new Doctor();
        //When
        doctorRepository.save(doctor);
        Optional<Doctor> output = doctorRepository.findById(doctor.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            doctorRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws DoctorException {
        //Given
        Doctor doctor = new Doctor();
        //When
        doctorRepository.save(doctor);
        Doctor output = doctorRepository.findById(doctor.getId()).orElseThrow(DoctorException::new);
        output.setFree(true);
        //Then
        assertTrue(output.isFree());
        //Cleanup
        try{
            doctorRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}