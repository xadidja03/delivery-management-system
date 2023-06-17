package com.example.demo.dto.request;
import com.example.demo.entity.model.FoodDetails;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodRequestDto {
    Long id;

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Description cannot be empty")
    String description;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive number")
    Double amount;

    @NotEmpty(message = "Image URL cannot be empty")
    String image;

    @Valid
    FoodDetails foodDetails;

    @NotNull(message = "Category ID cannot be null")
    Long category_id;
}
