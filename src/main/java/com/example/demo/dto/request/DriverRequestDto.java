package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequestDto {
    Long id;

    @NotNull(message = "isBusy field cannot be null")
    Boolean isBusy;

    @NotNull(message = "user_id field cannot be null")
    Long user_id;

    @AssertTrue(message = "Driver must be either busy or not busy")
    private boolean isBusyValid() {
        return isBusy != null && (isBusy || !isBusy);
    }
}
