package com.Hospital.Management.System.doclogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.docklogin.entity.Appointment;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}
