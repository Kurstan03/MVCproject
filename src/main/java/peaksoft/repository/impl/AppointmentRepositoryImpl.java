package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 18.02.2023 12:26
 */
@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Appointment> getAll(Long hospitalId) {
        return entityManager
                .createQuery("select a from Hospital h join h.appointments a where h.id = :id",
                        Appointment.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }

    @Override
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public Optional<Appointment> findById(Long appointmentId) {
//        Appointment appointment = entityManager.createQuery("from Appointment where id = :id", Appointment.class)
//                .setParameter("id", appointmentId)
//                .getSingleResult();
        Appointment appointment = entityManager.find(Appointment.class, appointmentId);
        return Optional.ofNullable(appointment);
    }

    @Override
    public void update(Long appointmentId, Appointment appointment) {
        entityManager
                .createQuery("update Appointment set date = :d, patient = :p, " +
                        "doctor = :doc, department = :dep where id = :id")
                .setParameter("d", appointment.getDate())
                .setParameter("p", appointment.getPatient())
                .setParameter("doc", appointment.getDoctor())
                .setParameter("dep", appointment.getDepartment())
                .setParameter("id", appointmentId)
                .executeUpdate();
    }

    @Override
    public void delete(Long appointmentId) {
//        entityManager.remove(
//                entityManager.createQuery("from Appointment where id = :id", Appointment.class)
//                        .setParameter("id", appointmentId)
//                        .getSingleResult()
        entityManager.createQuery("delete from Appointment where id = :id")
                .setParameter("id", appointmentId)
                .executeUpdate();

    }

    @Override
    public void deleteByDoctor(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size(); i++) {
//            entityManager.merge(appointments);
            entityManager.remove(appointments.get(i));
        }
    }

    @Override
    public void merge(Appointment appointment) {
        entityManager.merge(appointment);
    }
}
