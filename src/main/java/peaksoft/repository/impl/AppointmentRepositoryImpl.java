package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

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
}
