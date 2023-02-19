package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 12:25
 */
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Appointment> getAll(Long hospitalId) {
        List<Appointment> appointments = new ArrayList<>();
        int size = appointmentRepository.getAll(hospitalId).size();
        for (int i = 0; i < size; i++) {
            appointments.add(appointmentRepository.getAll(hospitalId).get(size - 1 - i));
        }
        return appointments;
    }
    @Override
    public void save(Long hospitalId, Appointment appointment) {
        Hospital hospital = hospitalRepository.getById(hospitalId);

        appointment.setDoctor(doctorRepository.findById(appointment.getDoctorId()));
        appointment.setPatient(patientRepository.getById(appointment.getPatientId()));
        appointment.setDepartment(departmentRepository.findById(appointment.getDepartmentId()));

        hospital.addAppointment(appointment);
        appointment.getPatient().addAppointment(appointment);
        appointment.getDoctor().addAppointment(appointment);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(Long appointmentId) {
        return null;
    }
}
