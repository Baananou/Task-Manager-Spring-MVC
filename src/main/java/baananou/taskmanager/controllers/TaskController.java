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
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {
    private ITaskService taskService;
    private ICategoryService categoryService;

    @GetMapping("/tasks")
    public String getAllTasks(Model m){
        m.addAttribute("tasks",taskService.getAllTasks());
        return "tasks";
    }
    @GetMapping("/tasks/{id}")
    public String getTasksById(Model m, @PathVariable Long id){
        m.addAttribute("task",taskService.getTaskById(id));
        return "showTask";
    }

    @GetMapping("/tasks/addForm")
    public String getAddTaskForm(Model m) {
        m.addAttribute("categories", categoryService.getAllCategories());
        Task task = new Task();
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        m.addAttribute("task", task);
        return "addTask";
    }

    @PostMapping("/tasks/add")
    public String addTask(@Valid Task task, Model m) {
        m.addAttribute("categories", categoryService.getAllCategories());
        // Ensure createdAt and updatedAt are set before saving
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskService.createTask(task);
        return "redirect:/tasks";
    }


    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    @GetMapping("/tasks/editForm/{id}")
    public String showUpdateForm(@PathVariable(name = "id") long id, Model m) {
        Task task = taskService.getTaskById(id);
        m.addAttribute("task", task);
        m.addAttribute("categories", categoryService.getAllCategories());
        return "editTask";
    }
    @PostMapping("/tasks/update/{id}")
    public String updateTask(@PathVariable(name = "id") long id, @ModelAttribute Task updatedTask) {
        // Set updatedAt field before updating the task
        updatedTask.setUpdatedAt(LocalDateTime.now());

        // Set the ID for the updated task
        updatedTask.setId(id);
        updatedTask.setCreatedAt(updatedTask.getCreatedAt());
        updatedTask.setUpdatedAt(LocalDateTime.now());
        taskService.createTask(updatedTask);
        return "redirect:/tasks";
    }

}
