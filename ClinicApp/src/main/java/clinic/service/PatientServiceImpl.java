package clinic.service;

import clinic.dto.PatientDTO;
import clinic.entity.Patient;
import clinic.entity.builder.PatientBuilder;
import clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient create(PatientDTO patientDTO) {
        Patient patient = new PatientBuilder()
                .setName(patientDTO.name)
                .setIdNumber(patientDTO.idNumber)
                .setDateOfBirth(Date.valueOf(patientDTO.dateOfBirth))
                .setAddress(patientDTO.address)
                .build();
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Integer id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findOne(id);
        patient.setId(id);
        patient.setName(patientDTO.name);
        patient.setIdNumber(patientDTO.idNumber);
        patient.setDateOfBirth(Date.valueOf(patientDTO.dateOfBirth));
        patient.setAddress(patientDTO.address);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return patientRepository.findOne(id);
    }

    @Override
    public List<Patient> searchForPatient(String info) {
        return patientRepository.searchForPatients("%" + info + "%");
    }
}
