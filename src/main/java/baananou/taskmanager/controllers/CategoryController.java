package baananou.taskmanager.controllers;

import baananou.taskmanager.services.ICategoryService;
import baananou.taskmanager.services.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CategoryController {
    private ITaskService taskService;
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public String getAllCategories(Model m){
        m.addAttribute("categories",categoryService.getAllCategories());
        return "categorie";
    }

    @GetMapping("/category/{id}")
    public String getTasksByCategory(Model m, Long id){
        m.addAttribute("tasks",taskService.getTasksByCategory(id));
        return "categorie";
    }
}
