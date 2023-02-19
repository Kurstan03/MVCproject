package peaksoft.repository;

import peaksoft.entity.Hospital;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 12:41
 */
public interface HospitalRepository {
    List<Hospital> getAllHospitals();

    void save(Hospital hospital);

    void delete(Long id);

    Hospital getById(Long id);

    void update(Long id, Hospital hospital);
}
