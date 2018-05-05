package clinic.service;

import clinic.dto.ConsultationDTO;
import clinic.entity.Consultation;
import clinic.entity.Doctor;
import clinic.entity.Patient;
import clinic.entity.builder.ConsultationBuilder;
import clinic.repository.ConsultationRepository;
import clinic.repository.DoctorRepository;
import clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.consultationRepository = consultationRepository;
    }

    private boolean availableConsultation(Integer id, Doctor doctor, Date date, LocalTime startTime, LocalTime endTime) {
        //find all consultations for the given doctor
        List<Consultation> consultations = consultationRepository.findByDoctor(doctor);
        //check availability
        for (Consultation c : consultations) {
            if ((id == 0 || id !=c.getId()) && (c.getDate().equals(date))) {
                if (endTime.compareTo(c.getStartTime()) >= 0 && endTime.compareTo(c.getEndTime()) <= 0)
                    return false;
                if (startTime.compareTo(c.getStartTime()) >= 0 && startTime.compareTo(c.getEndTime()) <= 0)
                    return false;
            }
        }
        return true;
    }

    @Override
    public Consultation create(Integer patientID, Integer doctorID, Date date, LocalTime startTime, LocalTime endTime) {
        //find patient
        Patient patient = patientRepository.findOne(patientID);
        //find doctor
        Doctor doctor = doctorRepository.findOne(doctorID);

        if (!availableConsultation(0, doctor, date, startTime, endTime))
            return null;

        Consultation consultation = new ConsultationBuilder()
                .setPatient(patient)
                .setDoctor(doctor)
                .setDate(date)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .build();
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Integer id, ConsultationDTO consultationDTO) {
        Patient patient = patientRepository.findOne(consultationDTO.patientID);

        Doctor doctor = doctorRepository.findOne(consultationDTO.doctorID);

        Date date = Date.valueOf(consultationDTO.date);
        LocalTime startTime = LocalTime.parse(consultationDTO.startTime);
        LocalTime endTime = LocalTime.parse(consultationDTO.endTime);
        if (!availableConsultation(id, doctor, date, startTime, endTime))
            return null;

        Consultation consultation = consultationRepository.findOne(id);
        consultation.setPatient(patient);
        consultation.setStartTime(startTime);
        consultation.setEndTime(endTime);
        consultation.setPatient(patient);
        consultation.setDoctor(doctor);
        consultation.setDate(date);

        return consultationRepository.save(consultation);
    }

    @Override
    public void addDiagnosis(Integer id, String diagnosis) {
        Consultation consultation = consultationRepository.findOne(id);
        consultation.setDiagnosis(diagnosis);
        consultationRepository.save(consultation);
    }


    @Override
    public Consultation findById(Integer id) {
        return consultationRepository.findOne(id);
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public List<Consultation> findPastConsultationsForPatient(Integer patientID) {
        Patient patient = patientRepository.findOne(patientID);
        return consultationRepository.findPastConsultationsForPatient(patient, new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Override
    public void delete(Integer id) {
        consultationRepository.delete(id);
    }
}
