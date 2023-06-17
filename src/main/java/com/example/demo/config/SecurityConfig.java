package com.example.demo.config;

import com.example.demo.service.impl.AppUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserDetailService appUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/drivers").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/drivers").hasAnyRole("DRIVER")
                .antMatchers(HttpMethod.GET, "/all/drivers").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/drivers/{id}").hasAnyRole("DRIVER")
                .antMatchers(HttpMethod.PUT, "/drivers/{id} ").hasAnyRole("DRIVER")
                .antMatchers(HttpMethod.DELETE, "/drivers/{id}" ).hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/").permitAll()

                .antMatchers(HttpMethod.POST,"/registration").hasRole("ADMIN")
                .antMatchers("/categories/findAll").permitAll()
                .antMatchers("/categories/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/categories/{id}").hasRole("ADMIN")
                .antMatchers("/categories/{id}/foods").permitAll()

                .antMatchers(HttpMethod.POST,"/customer/registration").hasRole("CUSTOMER")
                .antMatchers("/customer/confirm-account/").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/customer/confirm-account/").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET,"/customer/confirm-account/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST,"/food/registration").hasRole("ADMIN")
                .antMatchers("/food/findAll").permitAll()
                .antMatchers("/food//{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/food/{id}").hasRole("ADMIN")
                .antMatchers("/orders/findAll").hasRole("ADMIN")
                .antMatchers("/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/{id}").hasRole("DRIVER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }
}
