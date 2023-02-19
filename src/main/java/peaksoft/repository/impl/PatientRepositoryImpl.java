package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;


/**
 * @author kurstan
 * @created at 17.02.2023 22:55
 */
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Patient> getAll(Long hospitalId) {
        return entityManager
                .createQuery("from Patient p join p.hospital h where h.id = :id", Patient.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }

    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospital = entityManager.createQuery("from Hospital where id = :id", Hospital.class)
                .setParameter("id", hospitalId)
                .getSingleResult();
        patient.setHospital(hospital);
        hospital.addPatient(patient);
        entityManager.persist(hospital);
    }

    @Override
    public Patient getById(Long patientId) {
        return entityManager.createQuery("from Patient where id = :id", Patient.class)
                .setParameter("id", patientId)
                .getSingleResult();
    }

    @Override
    public void update(Long patientId, Patient patient) {
        entityManager.createQuery("update Patient set firstName = :f, lastName = :l," +
                "phoneNumber = :p, gender = :g, email = :e where id = :id")
                .setParameter("f", patient.getFirstName())
                .setParameter("l", patient.getLastName())
                .setParameter("p", patient.getPhoneNumber())
                .setParameter("g", patient.getGender())
                .setParameter("e", patient.getEmail())
                .setParameter("id", patientId)
                .executeUpdate();
    }

    @Override
    public void delete(Long patientId) {
        entityManager
                .remove(entityManager
                        .createQuery("from Patient where id = :id", Patient.class)
                        .setParameter("id", patientId)
                        .getSingleResult()
                );
    }
}