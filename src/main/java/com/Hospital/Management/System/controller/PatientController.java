package com.Hospital.Management.System.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.docklogin.entity.Appointment;
import com.Hospital.Management.System.entity.Patient;
import com.Hospital.Management.System.repository.PatientRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")

public class PatientController {

	
	private PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	@PostMapping("/patient")
	public Patient createPatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}
	
	@GetMapping
	public List<Patient>getAllPatient(){
		return patientRepository.findAll();
		
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws AttributeNotFoundException{
		Patient patient =patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Patient Not Found with Id: "+id));
		 	 return ResponseEntity.ok(patient);
	}
	
	 @DeleteMapping("/patient/{id}")
	    public ResponseEntity<Map<String, Boolean>> deletePatient(
	            @PathVariable long id) throws AttributeNotFoundException {

	        Patient patient = patientRepository.findById(id)
	            .orElseThrow(() ->
	                new AttributeNotFoundException("Patient not found with id " + id));

	        patientRepository.delete(patient);

	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);

	        return ResponseEntity.ok(response);
	    
	}
	 @PutMapping("/patient/{id}")
	 public ResponseEntity<Patient> updatePatient(
	         @PathVariable long id,
	         @RequestBody Patient patientDetails) throws AttributeNotFoundException {

	     Patient patient = patientRepository.findById(id)
	             .orElseThrow(() ->
	                new AttributeNotFoundException("Patient not found with id " + id));
	 
	     //  Update fields 
	     patient.setName(patientDetails.getName());
	     patient.setAge(patientDetails.getAge());
	     patient.setUrgency(patientDetails.getUrgency());
	     patient.setDose(patientDetails.getDose());
	     patient.setFees(patientDetails.getFees());
	     patient.setBlood(patientDetails.getBlood());
	     patient.setPrescription(patientDetails.getPrescription());

	     Patient updatedPatient = patientRepository.save(patient);

	     return ResponseEntity.ok(updatedPatient);
	     
	 }
}
