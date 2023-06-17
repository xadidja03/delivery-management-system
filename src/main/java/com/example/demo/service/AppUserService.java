package com.example.demo.service;

import com.example.demo.entity.model.AppUser;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    ResponseEntity<?> saveUser(AppUser appUser);
    ResponseEntity<?> confirmEmail(String confirmationToken);
}
