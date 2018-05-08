package clinic.dto;

public class ConsultationDTO {

    public int id;
    public String date;
    public String startTime;
    public String endTime;
    public int doctorID;
    public int patientID;

    public ConsultationDTO(String date, String startTime, String endTime, int doctorID, int patientID) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    public ConsultationDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "ConsultationDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                '}';
    }
}
