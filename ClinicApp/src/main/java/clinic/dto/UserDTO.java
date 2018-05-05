package clinic.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    public int id;

    @Size(min = 5, message = "The username is too short!")
    public String username;

    @Size(min = 7, message = "Password must be at least of length 7!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$", message = "Password must contain at least one character and one digit!")
    public String password;

    @Pattern(regexp = "ADMIN|SECRETARY|DOCTOR")
    public String role;

    public UserDTO(){}

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
