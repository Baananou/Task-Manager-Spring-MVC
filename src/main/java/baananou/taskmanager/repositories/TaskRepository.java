package baananou.taskmanager.repositories;

import baananou.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategory_Id(Long categoryId);

    // Additional methods if needed
    List<Task> findByTitle(String title);

    List<Task> findByIsCompleted(boolean isCompleted);

    List<Task> findByIsImportant(boolean isImportant);
}
