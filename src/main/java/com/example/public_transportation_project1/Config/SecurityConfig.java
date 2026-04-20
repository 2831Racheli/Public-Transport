package com.example.public_transportation_project1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // זה מאפשר את השימוש ב"דיקורטורים" (Annotations)
public class SecurityConfig {

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable()) // הכתיבה החדשה לביטול CSRF
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // הכל פתוח כברירת מחדל
        )
        .httpBasic(withDefaults -> {}); // מאפשר Basic Auth בפוסטמן
    return http.build();
}

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // כאן את מגדירה את המייל והסיסמה של המנהל (בלי מסד נתונים)
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("manager@bus.com") // המייל של המנהל
                .password("admin123")         // הסיסמה
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}