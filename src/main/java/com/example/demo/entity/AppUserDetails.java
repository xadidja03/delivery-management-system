package com.example.demo.entity;

import com.example.demo.entity.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class AppUserDetails extends AppUser implements UserDetails {
    public AppUserDetails(AppUser appUser){
        super.setName(appUser.getName());
        super.setSurname(appUser.getSurname());
        super.setPassword(appUser.getPassword());
        super.setRoles(appUser.getRoles());
        super.setBirthdate(appUser.getBirthdate());
        super.setEmail(appUser.getEmail());
        super.setPhoneNumber(appUser.getPhoneNumber());
        super.setRoles(appUser.getRoles());
        super.setIsEnabled(appUser.getIsEnabled());
        super.setUsername(appUser.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
        public String getPassword(){
        return getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled();
    }
}
