package clinic.service;

import clinic.dto.ConsultationDTO;
import clinic.entity.Consultation;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface ConsultationService {

    Consultation create(Integer patientID, Integer doctorID, Date date, LocalTime startTime, LocalTime endTime);

    Consultation update(Integer id, ConsultationDTO consultationDTO);

    void addDiagnosis(Integer id, String diagnosis);

    Consultation findById(Integer id);

    List<Consultation> findAll();

    List<Consultation> findPastConsultationsForPatient(Integer patientID);

    void delete(Integer id);

}
