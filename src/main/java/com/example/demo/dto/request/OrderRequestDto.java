package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    Long id;

    @NotEmpty(message = "Place cannot be empty")
    String place;

    @NotNull(message = "Cart ID cannot be null")
    Long cart_id;

    @NotNull(message = "Status cannot be null")
    @Min(value = 0, message = "Status must be a positive integer")
    Integer status;

    Long driver_id;
}
