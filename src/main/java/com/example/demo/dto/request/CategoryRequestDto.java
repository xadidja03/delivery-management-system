package com.example.demo.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequestDto {
    Long id;

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Description cannot be empty")
    String description;

    @NotNull(message = "Food ID cannot be null")
    Long food_id;

}
