package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.service.HospitalService;

/**
 * @author kurstan
 * @created at 17.02.2023 12:47
 */
@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "hospital/main";
    }
    @GetMapping("/new")
    public String createNewHospital(Model model){
        model.addAttribute("newHospital", new Hospital());
        return "hospital/new";
    }
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("hospital") Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteHospital(@PathVariable("id") Long id){
        hospitalService.delete(id);
        return "redirect:/hospitals";
    }
    @GetMapping("/{id}/edit")
    public String getUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/update";
    }
    @PatchMapping("/{id}")
    public String updateHospital(@PathVariable("id") Long id,
                                 @ModelAttribute("hospital") Hospital hospital){
        hospitalService.update(id, hospital);
        return "redirect:/hospitals";
    }
}
