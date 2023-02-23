package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 22:54
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Patient> getAll(Long patientId) {
        return patientRepository.getAll(patientId);
    }

    @Override
    public void save(Long hospitalId, Patient patient) {
        patientRepository.save(hospitalId, patient);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.getById(patientId)
                .orElseThrow(
                        ()-> new NotFoundException("Patient by id " + patientId + " not found"));
    }

    @Override
    public void update(Long patientId, Patient patient) {
        patientRepository.update(patientId, patient);
    }

    @Override
    public void delete(Long patientId) {
        Patient patient = patientRepository.getById(patientId)
                .orElseThrow(
                        ()-> new NotFoundException("Patient by id " + patientId + " not found"));
        Hospital hospital = patient.getHospital();
        List<Appointment> appointments = patient.getAppointments();
        appointments.forEach(a-> a.getPatient().setAppointments(null));
        appointments.forEach(a-> a.getDoctor().setAppointments(null));
//        appointments.forEach(a-> a.setPatient(null));
        hospital.getAppointments().removeAll(appointments);
        for (int i = 0; i < appointments.size(); i++) {
            appointmentRepository.delete(appointments.get(i).getId());
        }
        patientRepository.delete(patientId);
    }

    @Override
    public List<Appointment> getAppointments(Long id, Long patientId) {
        return appointmentRepository
                .getAll(id).stream().filter(a-> a.getPatient().getId()
                        .equals(patientId)).toList();
    }
}
