package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.service.DepartmentService;

/**
 * @author kurstan
 * @created at 18.02.2023 11:03
 */
@Controller
@RequestMapping("/{id}/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public String getAll(@PathVariable("id") Long id, Model model){
        model.addAttribute("departments", departmentService.getAll(id));
        return "department/departments";
    }
    @GetMapping("/new")
    public String create(@PathVariable("id") Long id, Model model){
        model.addAttribute("newDepartment", new Department());
        return "department/new";
    }
    @PostMapping("/save")
    public String save(@PathVariable("id") Long id,
                       @ModelAttribute("department") Department department){
        departmentService.save(id, department);
        return "redirect:/{id}/departments";
    }
    @GetMapping("/{departmentId}/edit")
    public String getUpdateForm(@PathVariable("id") Long id,
                                @PathVariable("departmentId") Long departmentId,
                                Model model){
        model.addAttribute("olDepartment", departmentService.findById(departmentId));
        return "department/update";
    }
    @PatchMapping("/{departmentId}/update")
    public String update(@PathVariable("id") Long id,
                         @PathVariable("departmentId") Long departmentId,
                         @ModelAttribute("department") Department department){
        departmentService.update(departmentId, department);
        return "redirect:/{id}/departments";
    }
    @DeleteMapping("{departmentId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("departmentId") Long departmentId){
        departmentService.delete(departmentId);
        return "redirect:/{id}/departments";
    }
}
