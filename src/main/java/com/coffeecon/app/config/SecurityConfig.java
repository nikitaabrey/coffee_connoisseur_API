package com.coffeecon.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {




        http.cors().and().csrf().disable()
                .authorizeRequests(authz ->
                        authz.antMatchers("/register","/confirm","/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())

                .oauth2ResourceServer().jwt();
        http.requiresChannel().anyRequest().requiresSecure();




        return http.build();
    }


}
