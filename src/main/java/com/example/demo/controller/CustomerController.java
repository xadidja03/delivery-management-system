package com.example.demo.controller;

import com.example.demo.entity.model.AppUser;
import com.example.demo.service.impl.AppUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CustomerController {
    private final AppUserDetailService appUserDetailService;

    @PostMapping("/customer/registration")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
        return appUserDetailService.saveUser(user);
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return appUserDetailService.confirmEmail(confirmationToken);
    }
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("@appUserDetailServiceImpl.hasCustomerId(@authentication,#customerId)")
    public Optional<AppUser> customer(@PathVariable Long customerId) {
        return appUserDetailService.findById(customerId);
    }
    @GetMapping("customer")
    public  String admin(){
        return "Hello Spring Security from admin!";
    }

}