package com.example.demo.entity.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.Embeddable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class FoodDetails{
    Double size;
    Double calories;
    Double price;

}