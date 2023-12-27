package baananou.taskmanager.services;

import baananou.taskmanager.models.Task;

import java.util.List;

public interface ITaskService {

    Task getTaskById(Long taskId);

    List<Task> getAllTasks();

    List<Task> getTasksByCategory(Long categoryId);

    Task createTask(Task task);

    Task updateTask(Long taskId, Task task);

    void deleteTask(Long taskId);

    void deleteTasksByCategory(long id);
}
