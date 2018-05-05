package clinic.entity.builder;

import clinic.entity.Patient;

import java.sql.Date;

public class PatientBuilder {

    private Patient patient;

    public PatientBuilder() {
        this.patient = new Patient();
    }

    public PatientBuilder setId(int id) {
        patient.setId(id);
        return this;
    }

    public PatientBuilder setName(String name) {
        patient.setName(name);
        return this;
    }

    public PatientBuilder setIdNumber(String idNumber) {
        patient.setIdNumber(idNumber);
        return this;
    }

    public PatientBuilder setDateOfBirth(Date dateOfBirth) {
        patient.setDateOfBirth(dateOfBirth);
        return this;
    }

    public PatientBuilder setAddress(String address) {
        patient.setAddress(address);
        return this;
    }

    public Patient build() {
        return patient;
    }
}
