package clinic.service;

import clinic.dto.PatientDTO;
import clinic.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient create(PatientDTO patientDTO);

    Patient update(Integer id, PatientDTO patientDTO);

    List<Patient> findAll();

    Patient findById(Integer id);

    List<Patient> searchForPatient(String info);

}
