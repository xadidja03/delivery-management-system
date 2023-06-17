package com.example.demo.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private List<String> roles;
    private Boolean isEnabled;
}
