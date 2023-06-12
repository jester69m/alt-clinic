package com.mis.altclinic.config;

import com.mis.altclinic.users.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/doctor_appointments","/consumers", "/consumers/add", "/consumers/edit/**", "consumers/delete/**").denyAll()
                        .requestMatchers(
                                "/", "/home", "/doctors", "/medservices",
                                "/login", "register", "/register/**"
                        ).permitAll()
                        .requestMatchers(
                                "/consumers/info", "doctor_appointments/consumer/**"
                        ).hasRole("CONSUMER")
                        .requestMatchers(
                                "doctor_appointments/doctor/**"
                        ).hasRole("DOCTOR")
                        .requestMatchers(
                                "/medservices/add", "/medservices/edit/**", "medservices/delete/**",
                                "/doctors/add", "/doctors/edit/**", "doctors/delete/**"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.defaultSuccessUrl("/", true))
                .logout(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }
}
