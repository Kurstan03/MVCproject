package peaksoft.repository;

import peaksoft.entity.Appointment;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 18.02.2023 12:25
 */
public interface AppointmentRepository {
    List<Appointment> getAll(Long hospitalId);

    void save(Appointment appointment);

    Optional<Appointment> findById(Long appointmentId);

    void update(Long appointmentId, Appointment appointment);

    void delete(Long appointmentId);

    void deleteByDoctor(List<Appointment> appointments);

    void merge(Appointment appointment);
}
