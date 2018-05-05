package clinic.controller.doctor;

import clinic.entity.Consultation;
import clinic.service.ConsultationService;
import clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    private ConsultationService consultationService;
    private PatientService patientService;

    @Autowired
    public DoctorController(ConsultationService consultationService, PatientService patientService) {
        this.consultationService = consultationService;
        this.patientService = patientService;
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String showDoctorPage() {
        return "doctor-page";
    }

    @RequestMapping(value = "/doctor/find-patients", method = RequestMethod.POST)
    public String searchForPatients(Model model, @RequestParam(value = "patientInfo") String info) {
        model.addAttribute("patients", patientService.searchForPatient(info));
        return "patients-list-doctor";
    }

    @RequestMapping(value = "/doctor/findpast/{patientID}", method = RequestMethod.GET)
    public String findAllPast(Model model, @PathVariable(value="patientID") Integer patientID) {
        model.addAttribute("consultations", consultationService.findPastConsultationsForPatient(patientID));
        return "consultations-list-doctor";
    }

    @RequestMapping(value = "/doctor/consultation/diagnosis/{consultationID}", method = RequestMethod.POST)
    public String addDiagnosis(@RequestParam(value = "details") String diagnosis, @PathVariable(value = "consultationID") Integer consultationID) {
        Consultation consultation = consultationService.findById(consultationID);
        consultationService.addDiagnosis(consultationID, diagnosis);
        return "redirect:/doctor/findpast/" + consultation.getPatient().getId();
    }
}
