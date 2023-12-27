package baananou.taskmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    public List<Task> tasks;
}