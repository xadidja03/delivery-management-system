package com.example.demo.dto.response;

import com.example.demo.entity.model.FoodDetails;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FoodResponseDto {
    Long id;
    String name;
    String description;
    Double amount;
    String image;
    FoodDetails foodDetails;
    Long category_id;
}