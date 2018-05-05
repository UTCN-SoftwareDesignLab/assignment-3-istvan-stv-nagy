package clinic.entity.builder;

import clinic.entity.Consultation;
import clinic.entity.Doctor;
import clinic.entity.Patient;

import java.sql.Date;
import java.time.LocalTime;

public class ConsultationBuilder {

    private Consultation consultation;

    public ConsultationBuilder() {
        this.consultation = new Consultation();
    }

    public ConsultationBuilder setId(int id) {
        consultation.setId(id);
        return this;
    }

    public ConsultationBuilder setDate(Date date) {
        consultation.setDate(date);
        return this;
    }

    public ConsultationBuilder setStartTime(LocalTime time) {
        consultation.setStartTime(time);
        return this;
    }

    public ConsultationBuilder setEndTime(LocalTime time) {
        consultation.setEndTime(time);
        return this;
    }

    public ConsultationBuilder setPatient(Patient patient) {
        consultation.setPatient(patient);
        return this;
    }

    public ConsultationBuilder setDoctor(Doctor doctor) {
        consultation.setDoctor(doctor);
        return this;
    }

    public Consultation build() {
        return consultation;
    }
}
