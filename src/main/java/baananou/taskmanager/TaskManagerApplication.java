package baananou.taskmanager;

import baananou.taskmanager.models.Category;
import baananou.taskmanager.models.Task;
import baananou.taskmanager.repositories.CategoryRepository;
import baananou.taskmanager.repositories.TaskRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

@SpringBootApplication
public class TaskManagerApplication {
	Faker faker = new Faker();
	Calendar calendar = Calendar.getInstance();



	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(TaskRepository taskRepository, CategoryRepository categoryRepository)
	{
		return args -> {

			categoryRepository.save(Category.builder()
					.name(faker.lorem().word())
					.description(faker.lorem().sentence())
					.build());
			calendar.add(Calendar.DAY_OF_MONTH, faker.random().nextInt(30) + 1); // Adding 1 to avoid getting the current date
			taskRepository.save(Task.builder()
					.title(faker.lorem().sentence())
					.description(faker.lorem().paragraph())
					.date(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()))
					.isCompleted(faker.random().nextBoolean())
					.isImportant(faker.random().nextBoolean())
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.category(categoryRepository.findById(2L).get())
					.build());
		};
	}
}
