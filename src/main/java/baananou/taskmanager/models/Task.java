package baananou.taskmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Date is required")
    private String date;

    private boolean isCompleted = false;

    private boolean isImportant = false;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotBlank(message = "User ID is required")
    private String userId;

    @ManyToOne
    @NotNull(message = "Category cannot be null")
    private Category category;

}