package com.example.demo.repository;

import com.example.demo.entity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsernameAndIsEnabled(String username, boolean enable);
    Optional<AppUser> findByUsername(String username);
    AppUser findByEmailIgnoreCase(String emailId);
    Boolean existsByEmail(String email);
    @Query("SELECT au FROM AppUser au WHERE au.email = :email")
    Optional<AppUser> findByEmail(@Param("email") String email);


}