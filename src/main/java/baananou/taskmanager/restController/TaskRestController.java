package baananou.taskmanager.restController;

import baananou.taskmanager.models.Task;
import baananou.taskmanager.services.ICategoryService;
import baananou.taskmanager.services.ITaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskRestController {
    private ITaskService taskService;
    private ICategoryService categoryService;

    //Get all tasks Working
    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    //Get task by id Working
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    //Create New Task Working
    @PostMapping("/add")
    public void addTask(@Valid @RequestBody Task task) {
        // Ensure createdAt and updatedAt are set before saving
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskService.createTask(task);
    }
    //Delete Task Working
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable(name = "id") long id) {
        taskService.deleteTask(id);
    }
    //Update Task Not Working
    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable(name = "id") long id, @Valid @RequestBody Task updatedTask) {
        // Set updatedAt field before updating the task
        updatedTask.setUpdatedAt(LocalDateTime.now());

        // Set the ID for the updated task
        updatedTask.setId(id);
        updatedTask.setCreatedAt(updatedTask.getCreatedAt());
        updatedTask.setUpdatedAt(LocalDateTime.now());
        taskService.updateTask(id,updatedTask);
    }
}
