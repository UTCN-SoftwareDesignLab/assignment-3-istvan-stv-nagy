package clinic.repository;

import clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select p from Patient p where p.name LIKE ?1 OR p.idNumber LIKE ?1")
    List<Patient> searchForPatients(String field);

}
