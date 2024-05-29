package com.capstone.petcare2.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/auth/forgotPassword").permitAll()
        .requestMatchers(HttpMethod.PUT, "/api/v1/auth/resetPassword").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/auth/validateOtp").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/auth/changePassword").permitAll()
                .requestMatchers(HttpMethod.POST, "/addUser").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/auth/updateUser").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/auth/getuser").permitAll()
                .requestMatchers(HttpMethod.PUT, "/updateUser/{userId}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/deleteUser/{userId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/getUser/{userId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/getAllUser").permitAll()
                .requestMatchers(HttpMethod.GET, "/getAllPets").permitAll()
                .requestMatchers(HttpMethod.GET, "/getPet/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/addPet").permitAll()
                .requestMatchers(HttpMethod.PUT, "/updatePet/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/deletePet/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/addAdmin").permitAll()
                .requestMatchers(HttpMethod.GET, "/getAllAdmin").permitAll()
                .requestMatchers(HttpMethod.GET, "/getAdmin/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/deleteAdmin/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/updateAdmin/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/getAllBooking").permitAll()
                .requestMatchers(HttpMethod.GET, "/getBooking/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/bookAppointment").permitAll()
                .requestMatchers(HttpMethod.PUT, "/updateBooking/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/deleteBooking/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/generate-bill").permitAll()
                .requestMatchers(HttpMethod.POST,"/createBooking").permitAll()
                .requestMatchers(HttpMethod.GET,"/getPetByOwnerEmail").permitAll()
                .requestMatchers(HttpMethod.GET,"/getBookingByOwnerEmail").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/deleteBooking").permitAll()
                // All other endpoints require authentication
                .anyRequest().authenticated()
                );
                return httpSecurity.build();
            }
        }
