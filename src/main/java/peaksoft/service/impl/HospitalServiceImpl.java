package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 12:45
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.getAllHospitals();
    }

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public void delete(Long id) {
        hospitalRepository.delete(id);
    }

    @Override
    public Hospital getById(Long id) {
        return hospitalRepository.getById(id);
    }

    @Override
    public void update(Long id, Hospital hospital) {
        hospitalRepository.update(id, hospital);
    }
}
