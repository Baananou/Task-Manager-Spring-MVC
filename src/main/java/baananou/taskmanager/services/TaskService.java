package baananou.taskmanager.services;

import baananou.taskmanager.models.Task;
import baananou.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService{
private TaskRepository taskRepository;
    @Override
    public Task getTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElse(null);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByCategory(Long categoryId) {
        return taskRepository.findByCategory_Id(categoryId);
    }

    @Override
    public Task createTask(Task task) {
        validateTaskBeforeCreate(task);
        return taskRepository.save(task);
    }

    private void validateTaskBeforeCreate(Task task) {
        // Validate title
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        // Validate date
        if (task.getDate() == null || task.getDate().isEmpty()) {
            throw new IllegalArgumentException("Date is required");
        }
    }

    @Override
    public Task updateTask(Long taskId, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDate(updatedTask.getDate());
            existingTask.setCompleted(updatedTask.isCompleted());
            existingTask.setImportant(updatedTask.isImportant());
            existingTask.setCreatedAt(updatedTask.getCreatedAt());
            existingTask.setUpdatedAt(updatedTask.getUpdatedAt());
            existingTask.setCategory(updatedTask.getCategory());
            return taskRepository.save(existingTask);
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " not found");
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void deleteTasksByCategory(long id) {
        List<Task> tasks = taskRepository.findByCategory_Id(id);
        for (Task task : tasks) {
            taskRepository.deleteById(task.getId());
        }
    }


}