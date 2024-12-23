package com.HotelBooking.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
       http.csrf().disable().cors().disable();
       http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

//       http.authorizeHttpRequests().anyRequest().permitAll();
//         only those url are allowed which have only valid token and roles
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/users/userLogin","/api/v1/users/signup","/api/v1/users/signup-propertyOwner").permitAll()
                .requestMatchers("/api/v1/country/add").hasAnyRole("OWNER","ADMIN")

                .anyRequest().authenticated();


       return http.build();
    }

}
