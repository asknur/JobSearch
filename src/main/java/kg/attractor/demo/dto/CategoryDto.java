package kg.attractor.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    @Min(1)
    @Positive
    private int parent_id;
}
