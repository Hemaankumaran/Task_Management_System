package com.springboot.taskmgmtsystem.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private JwtFilter jwtFilter;

    @Bean
    // ADMIN -> admin - admin
    // CUSTOMER -> ron - ron, dhoni - dhoni
    public SecurityFilterChain bankingSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/auth/login")
                                .authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/admin/add")
                                .permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/customer/signup")
                                .permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/task/getall")
                                .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/task/get/{taskId}")
                                .authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/task/add/{customerId}")
                                .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/task/update/{taskId}")
                                .authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/task/delete/{taskId}")
                                .authenticated()

                        .anyRequest().permitAll()
                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
