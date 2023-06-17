package com.example.demo.entity.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    LocalDateTime created_at;
    LocalDateTime confirmed_at;
    Boolean confirm;
    @OneToOne
    AppUser user;
    public ConfirmationToken(AppUser appUser) {
        this.user = appUser;
    }
}

