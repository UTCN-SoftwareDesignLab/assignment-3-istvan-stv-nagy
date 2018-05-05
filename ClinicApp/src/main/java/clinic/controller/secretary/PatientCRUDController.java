package clinic.controller.secretary;

import clinic.controller.ErrorExtractor;
import clinic.dto.PatientDTO;
import clinic.entity.Patient;
import clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/secretary/patient")
public class PatientCRUDController {

    private PatientService patientService;

    @Autowired
    public PatientCRUDController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPatient(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "patient-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPatient(Model model, @ModelAttribute("patient") @Valid PatientDTO patientDTO, BindingResult result) {
        if (!result.hasErrors()) {
            patientService.create(patientDTO);
            return "redirect:findall";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            return "patient-form";
        }
    }

    @RequestMapping(value = "/update/{patientID}", method = RequestMethod.GET)
    public String updatePatient(Model model, @PathVariable(value = "patientID") Integer id) {
        Patient patient = patientService.findById(id);
        PatientDTO patientDTO = new PatientDTO(patient.getName(), patient.getIdNumber(), patient.getDateOfBirth().toString(), patient.getAddress());
        patientDTO.setId(id);
        model.addAttribute("patient", patientDTO);
        return "patient-update-form";
    }

    @RequestMapping(value = "/update/{patientID}", method = RequestMethod.POST)
    public String updatePatient(Model model, @PathVariable(value = "patientID") Integer id, @ModelAttribute(value = "patient") @Valid PatientDTO patientDTO, BindingResult result) {
        if (!result.hasErrors()) {
            patientService.update(id, patientDTO);
            return "redirect:/secretary/patient/findall";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            return "patient-update-form";
        }

    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public String findAllPatients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "patients-list";
    }

}
