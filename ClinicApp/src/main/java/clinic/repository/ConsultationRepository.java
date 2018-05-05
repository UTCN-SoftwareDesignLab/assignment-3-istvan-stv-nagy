package clinic.repository;

import clinic.entity.Consultation;
import clinic.entity.Doctor;
import clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    List<Consultation> findByDoctor(Doctor doctor);


    @Query("select c from Consultation c where c.patient = ?1 AND c.date < ?2")
    List<Consultation> findPastConsultationsForPatient(Patient patient, Date date);

}
