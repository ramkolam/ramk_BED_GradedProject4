package com.greatlearning.ems.config;

import com.greatlearning.ems.service.impl.EmsUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new EmsUserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/roles", "/api/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers(HttpMethod.POST, "*/employees").hasAnyAuthority("ADMIN")
                            .anyRequest()
                            .authenticated();
                })
                .logout(withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults());

        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

}
