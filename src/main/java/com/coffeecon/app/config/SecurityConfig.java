package com.coffeecon.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {


    @Autowired
    private JwtAuthEntry jwtAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {




        http.cors().and().csrf().disable()
                .authorizeRequests(authz ->
                        authz.antMatchers("/register","/otp/resend","/login","/otp/confirmation",
                                        "/v2/api-docs",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui.html",
                                        "/webjars/**",
                                        // -- Swagger UI v3 (OpenAPI)
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**","/")
                        .permitAll()
                        .anyRequest()
                        .authenticated())

                .oauth2ResourceServer().authenticationEntryPoint(jwtAuthenticationEntryPoint).jwt();
        http.requiresChannel().anyRequest().requiresSecure();


        return http.build();
    }


}
