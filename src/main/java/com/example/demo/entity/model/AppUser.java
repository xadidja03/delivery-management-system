package com.example.demo.entity.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String username;
    LocalDate birthdate;
    String email;
    String password;
    String phoneNumber;
    @ManyToMany(mappedBy = "appUsers",fetch = FetchType.EAGER)
    List<Role> roles;
    @Builder.Default
    Boolean isEnabled=false;

}
