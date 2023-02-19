package peaksoft.repository;

import peaksoft.entity.Appointment;

import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 12:25
 */
public interface AppointmentRepository {
    List<Appointment> getAll(Long hospitalId);

    void save(Appointment appointment);
}
