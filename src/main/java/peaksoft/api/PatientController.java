package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Patient;
import peaksoft.service.PatientService;

/**
 * @author kurstan
 * @created at 17.02.2023 22:51
 */
@Controller
@RequestMapping("/{id}/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping
    public String getAll(@PathVariable("id") Long id, Model model){
        model.addAttribute("patients", patientService.getAll(id));
        return "patient/patients";
    }
    @GetMapping("/new")
    public String createNewPatient(@PathVariable("id") Long id, Model model){
        model.addAttribute("newPatient", new Patient());
        return "patient/new";
    }
    @PostMapping("/save")
    public String savePatient(@PathVariable("id") Long id,
                              @ModelAttribute("patient") Patient patient){
        patientService.save(id, patient);
        return "redirect:/{id}/patients";
    }
    @GetMapping("/{patientId}/edit")
    private String getUpdateForm(@PathVariable("patientId") Long patientId,
                                 Model model,
                                 @PathVariable("id") Long id){
        model.addAttribute("patient", patientService.getById(patientId));
        return "patient/update";
    }
    @PatchMapping("/{patientId}")
    private String updatePatient(@PathVariable("id") Long id,
                                 @PathVariable("patientId") Long patientId,
                                 @ModelAttribute("patient") Patient patient){
        patientService.update(patientId, patient);
        return "redirect:/{id}/patients";
    }
    @DeleteMapping("/{patientId}/delete")
    public String deletePatient(@PathVariable("id") Long id,
                                @PathVariable("patientId") Long patientId){
        patientService.delete(patientId);
        return "redirect:/{id}/patients";
    }
}
