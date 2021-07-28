package com.kodilla.kodilla.clinic.domain;

import com.kodilla.kodilla.clinic.exception.DoctorException;
import com.kodilla.kodilla.clinic.exception.PatientException;
import com.kodilla.kodilla.clinic.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientTestSuite {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testCreate(){
        //Given
        Patient patient = new Patient();
        //When
        patientRepository.save(patient);
        //Then
        assertEquals(1,patientRepository.count());
        //Cleanup
        try {
            patientRepository.deleteAll();
        }catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete(){
        //Given
        Patient patient = new Patient();
        //When&Then
        patientRepository.save(patient);
        assertEquals(1,patientRepository.count());
        patientRepository.deleteById(patient.getId());
        assertEquals(0,patientRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Patient patient = new Patient();
        //When
        patientRepository.save(patient);
        Optional<Patient> output = patientRepository.findById(patient.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            patientRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws PatientException {
        //Given
        Patient patient = new Patient();
        //When
        patientRepository.save(patient);
        Patient output = patientRepository.findById(patient.getId()).orElseThrow(PatientException::new);
        output.setMedicalHistory("TestHistory");
        //Then
        assertEquals("TestHistory", output.getMedicalHistory());
        //Cleanup
        try{
            patientRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}