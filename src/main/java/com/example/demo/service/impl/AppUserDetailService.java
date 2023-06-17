package com.example.demo.service.impl;

import com.example.demo.entity.model.AppUser;
import com.example.demo.entity.AppUserDetails;
import com.example.demo.entity.model.ConfirmationToken;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService, AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsernameAndIsEnabled(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found! " + username));
        return new AppUserDetails(appUser);
    }
    public Boolean hasCustomerId(Authentication authentication,Long customer_id) {
       String username= authentication.getName();
       Optional<AppUser> byUsername=appUserRepository.findByUsername(username);
       return  byUsername.isPresent() && Objects.equals(byUsername.get().getId(),customer_id);
    }


    public ResponseEntity<?> saveUser(AppUser appUser) {

        if (appUserRepository.existsByEmail(appUser.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .created_at(LocalDateTime.now())
                .confirmed_at(LocalDateTime.now())
                .user(appUser)
                .confirm(true)
                .build();

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(appUser.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:1357/confirm-account?token="+confirmationToken.getToken());

        confirmationTokenRepository.save(confirmationToken);
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByToken(confirmationToken);

        log.info("confirmation token : " + confirmationToken);

        if(token != null)
        {
            AppUser appUser = appUserRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            log.info("Find email : " + token.getUser().getEmail());
            appUser.setIsEnabled(true);
            appUserRepository.save(appUser);
            log.info("Email verified successfully!");
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
    public List<String> findAll() {
        return appUserRepository.findAll().stream()
                .map(AppUser::getUsername)
                .collect(Collectors.toList());


    }

    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

}

