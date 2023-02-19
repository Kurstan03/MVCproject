package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Doctor;
import peaksoft.service.DoctorService;

/**
 * @author kurstan
 * @created at 18.02.2023 4:07
 */
@Controller
@RequestMapping("/{id}/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping()
    public String getAll(@PathVariable("id") Long id, Model model){
        model.addAttribute("doctors", doctorService.getAll(id));
        return "doctor/doctors";
    }

    @GetMapping("/new")
    public String createNewDoctor(@PathVariable("id") Long id, Model model){
        model.addAttribute("newDoctor", new Doctor());
        return "doctor/new";
    }
    @PostMapping("/save")
    public String save(@PathVariable("id") Long id,
                       @ModelAttribute("doctor") Doctor doctor){
        doctorService.save(id, doctor);
        return "redirect:/{id}/doctors";
    }
    @GetMapping("/{doctorId}/edit")
    public String getUpdateFrom(@PathVariable("id") Long id,
                                @PathVariable("doctorId") Long doctorId,
                                Model model){
        model.addAttribute("oldDoctor", doctorService.findById(doctorId));
        return "doctor/update";
    }
    @PatchMapping("/{doctorId}/update")
    public String update(@PathVariable("id") Long id,
                         @PathVariable("doctorId") Long doctorId,
                         @ModelAttribute("doctor") Doctor doctor){
        doctorService.update(doctorId, doctor);
        return "redirect:/{id}/doctors";
    }
    @DeleteMapping("/{doctorId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("doctorId") Long doctorId){
        doctorService.delete(doctorId);
        return "redirect:/{id}/doctors";
    }
}
