package clinic.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String idNumber;

    private Date dateOfBirth;

    private String address;

    public Patient(String name, String idNumber, Date dateOfBirth, String address) {
        this.name = name;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Patient() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
