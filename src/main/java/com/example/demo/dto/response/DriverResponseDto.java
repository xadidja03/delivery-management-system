package com.example.demo.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponseDto {
    Long id;
    Boolean isBusy;
    Long user_id;
}
