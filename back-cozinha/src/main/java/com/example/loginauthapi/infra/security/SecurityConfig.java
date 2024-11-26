package com.example.loginauthapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/funcionario/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/funcionario/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/funcionario/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/funcionario/softDelete/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/avaliacao/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/avaliacao/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/avaliacao/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/avaliacao/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/livros/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/livros/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/livros/{id}").permitAll()
                        
                        .requestMatchers(HttpMethod.POST, "/cargo").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cargo/").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/cargo/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/categoria/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categoria/").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/categoria/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/receitas/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/receitas/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/receitas/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/receitas/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}