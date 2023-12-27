package baananou.taskmanager.restController;

import baananou.taskmanager.models.Category;
import baananou.taskmanager.models.Task;
import baananou.taskmanager.services.ICategoryService;
import baananou.taskmanager.services.ITaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryRestController {
    private ITaskService taskService;
    private ICategoryService categoryService;
    //Get all categories Working
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    //Get category by id Working
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    //Create New Category Working
    @PostMapping("/add")
    public void addCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
    }
    //delete category Working
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable(name = "id") long id) {
        // Check if there are tasks associated with the category
        List<Task> tasks = taskService.getTasksByCategory(id);

        if (!tasks.isEmpty()) {
            // Delete tasks associated with the category
            taskService.deleteTasksByCategory(id);
        }

        // Now you can safely delete the category
        categoryService.deleteCategory(id);
    }
    //Update Category Working
    @PutMapping("/update/{id}")
    public void updateCategory(@PathVariable(name = "id") long id, @Valid @RequestBody Category category) {
        category.setId(id);
        categoryService.updateCategory(id, category);
    }
}
