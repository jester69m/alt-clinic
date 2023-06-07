package com.mis.altclinic.config;

import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import com.mis.altclinic.users.UserDetailsService;
import com.mis.altclinic.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                        .requestMatchers("/", "/home","/doctors", "/medservices").permitAll()
                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/doctors/**", "/medservices/**", "/doctor_appointment/**","/consumers/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .email("admin")
//                .password(passwordEncoder().encode("123"))
//                .role(Role.ROLE_ADMIN)
//                .build();
//
//        UserDetails consumer = User.builder()
//                .email("consumer")
//                .password(passwordEncoder().encode("123"))
//                .role(Role.ROLE_CONSUMER)
//                .build();
//
//        UserDetails doctor = User.builder()
//                .email("doctor")
//                .password(passwordEncoder().encode("123"))
//                .role(Role.ROLE_DOCTOR)
//                .build();
//        return new InMemoryUserDetailsManager(admin,consumer, doctor);
//    }

    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
