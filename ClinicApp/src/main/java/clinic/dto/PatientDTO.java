package clinic.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatientDTO {

    public int id;

    @Size(min = 5, message = "The name is too short!")
    public String name;

    @Pattern(regexp = "[1-2][0-9]{12}", message = "Invalid id number!")
    public String idNumber;

    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}",message = "Invalid Date of birth!")
    public String dateOfBirth;

    @Size(min = 5, message = "Invalid address!")
    public String address;

    public PatientDTO(String name, String idNumber, String dateOfBirth, String address) {
        this.name = name;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public PatientDTO(){}

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
