package clinic.controller.secretary;

import clinic.dto.ConsultationDTO;
import clinic.entity.Consultation;
import clinic.service.ConsultationService;
import clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.time.LocalTime;

@Controller
@RequestMapping("/secretary/consultation")
public class ConsultationCRUDController {

    private ConsultationService consultationService;
    private DoctorService doctorService;

    @Autowired
    public ConsultationCRUDController(ConsultationService consultationService, DoctorService doctorService) {
        this.consultationService = consultationService;
        this.doctorService = doctorService;
    }

    @RequestMapping(value = "/create/{patientID}", method = RequestMethod.GET)
    public String createConsultation(Model model, @PathVariable(value = "patientID") Integer patientID) {
        model.addAttribute("doctors", doctorService.findAll());
        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setPatientID(patientID);
        model.addAttribute("consultation", consultationDTO);
        return "consultation-form";
    }

    @RequestMapping(value = "/create/{patientID}", method = RequestMethod.POST)
    public String createConsultation(@ModelAttribute(value = "consultation") ConsultationDTO consultationDTO, @PathVariable(value="patientID")Integer patientID) {
        Integer doctorID = consultationDTO.doctorID;
        Date date = Date.valueOf(consultationDTO.date);
        LocalTime startTime = LocalTime.parse(consultationDTO.startTime);
        LocalTime endTime = LocalTime.parse(consultationDTO.endTime);
        consultationService.create(patientID, doctorID, date, startTime, endTime);
        return "redirect:/secretary/consultation/findall";
    }

    @RequestMapping(value = "/delete/{consultationID}", method = RequestMethod.GET)
    public String deleteConsultation(@PathVariable(value = "consultationID") Integer id) {
        consultationService.delete(id);
        return "redirect:/secretary/consultation/findall";
    }

    @RequestMapping(value = "/update/{consultationID}", method = RequestMethod.GET)
    public String updateConsultation(Model model, @PathVariable(value = "consultationID") Integer id) {
        model.addAttribute("doctors", doctorService.findAll());
        Consultation consultation = consultationService.findById(id);
        String date = consultation.getDate().toString();
        String startTime = consultation.getStartTime().toString();
        String endTime = consultation.getEndTime().toString();
        Integer doctorID = consultation.getDoctor().getId();
        Integer patientID = consultation.getPatient().getId();
        ConsultationDTO consultationDTO = new ConsultationDTO(date, startTime, endTime, doctorID, patientID);
        consultationDTO.setId(id);
        model.addAttribute("consultation", consultationDTO);
        return "consultation-update-form";
    }

    @RequestMapping(value = "/update/{consultationID}", method = RequestMethod.POST)
    public String updateConsultation(@PathVariable(value="consultationID") Integer id, @ModelAttribute(value="consultation") ConsultationDTO consultationDTO) {
        consultationService.update(id, consultationDTO);
        return "redirect:/secretary/consultation/findall";
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public String findAllConsultations(Model model) {
        model.addAttribute("consultations", consultationService.findAll());
        return "consultations-list";
    }
}
