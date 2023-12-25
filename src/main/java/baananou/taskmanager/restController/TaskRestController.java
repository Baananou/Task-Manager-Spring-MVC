package baananou.taskmanager.restController;

import baananou.taskmanager.services.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskRestController {
}
