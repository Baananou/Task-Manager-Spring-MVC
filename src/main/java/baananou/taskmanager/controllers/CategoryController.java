package baananou.taskmanager.controllers;

import baananou.taskmanager.models.Category;
import baananou.taskmanager.models.Task;
import baananou.taskmanager.services.ICategoryService;
import baananou.taskmanager.services.ITaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {
    private ITaskService taskService;
    private ICategoryService categoryService;

    @GetMapping("/category")
    public String getAllCategories(Model m){
        m.addAttribute("categories",categoryService.getAllCategories());
        return "category";
    }

    @GetMapping("/category/{id}")
    public String getCategoryById(Model m, @PathVariable("id") Long id){
        m.addAttribute("category",categoryService.getCategoryById(id));
        return "showCategory";
    }

    @GetMapping("/category/addForm")
    public String getAddCategoryForm(Model m){
        m.addAttribute("category");
        return "addCategory";
    }
    @PostMapping("/category/add")
    public String addCategory(@Valid Category category){
        categoryService.createCategory(category);
                return "redirect:/category";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        // Check if there are tasks associated with the category
        List<Task> tasks = taskService.getTasksByCategory(id);

        if (!tasks.isEmpty()) {
            // Delete tasks associated with the category
            taskService.deleteTasksByCategory(id);
        }

        // Now you can safely delete the category
        categoryService.deleteCategory(id);

        return "redirect:/category";
    }
    @GetMapping("/category/editForm/{id}")
    public String showUpdateForm(@PathVariable long id, Model m) {
        Category category = categoryService.getCategoryById(id);
        m.addAttribute("category", category);
        m.addAttribute("categories", categoryService.getAllCategories());
        return "editCategory";
    }
    @PostMapping("/category/update/{id}")
    public String updateCategory(@PathVariable(name = "id") long id, @ModelAttribute Category category) {
        category.setId(id);
        categoryService.updateCategory(id,category);

        return "redirect:/category";
    }

}
