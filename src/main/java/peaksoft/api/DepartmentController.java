package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.exeptions.NotFoundException;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

/**
 * @author kurstan
 * @created at 18.02.2023 11:03
 */
@Controller
@RequestMapping("/{id}/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }
    @GetMapping
    public String getAll(@PathVariable("id") Long id, Model model){
        try {
            model.addAttribute("hospital", hospitalService.getById(id));
            model.addAttribute("departments", departmentService.getAll(id));
            return "department/departments";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }
    @GetMapping("/new")
    public String create(@PathVariable("id") Long id, Model model){
        model.addAttribute("department", new Department());
        return "department/new";
    }
    @PostMapping("/save")
    public String save(@PathVariable("id") Long id,
                       @ModelAttribute("department")@Valid Department department,
                       BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "department/new";
        }
        try {
            departmentService.save(id, department);
            return "redirect:/{id}/departments";
        } catch (DataIntegrityViolationException e){
            model.addAttribute("Name", "This department already exists in the database");
            return "department/new";
        }
    }
    @GetMapping("/{departmentId}/edit")
    public String getUpdateForm(@PathVariable("id") Long id,
                                @PathVariable("departmentId") Long departmentId,
                                Model model){
        try {
            model.addAttribute("department", departmentService.findById(departmentId));
            return "department/update";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }
    @PatchMapping("/{departmentId}/update")
    public String update(@PathVariable("id") Long id,
                         @PathVariable("departmentId") Long departmentId,
                         @ModelAttribute("department")@Valid Department department,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "department/update";
        }
        try {
            departmentService.update(departmentId, department);
            return "redirect:/{id}/departments";
        } catch (DataIntegrityViolationException e){
            model.addAttribute("Name", "This department already exists in the database");
            return "department/update";
        }
    }
    @DeleteMapping("/{departmentId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("departmentId") Long departmentId){
        departmentService.delete(departmentId);
        return "redirect:/{id}/departments";
    }
    @GetMapping("/{departmentId}/doctors")
    public String getDoctors(@PathVariable("id") Long id,
                             @PathVariable("departmentId") Long departmentId,
                             Model model){
        try {
            model.addAttribute("department", departmentService.findById(departmentId));
            model.addAttribute("doctors", departmentService.getDoctors(id, departmentId));
            return "department/doctors";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }
}
