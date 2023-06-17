package com.example.demo.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppUserRequestDto {

    @NotEmpty(message = "Name is required")
    String name;

    @NotEmpty(message = "Surname is required")
    String surname;

    @NotEmpty(message = "Username is required")
    String username;

    LocalDate birthdate;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password should contain at least one letter and one number")
    String password;

    @NotEmpty(message = "Phone number is required")
    String phoneNumber;

}
