package com.quid.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
            .antMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
            .antMatchers("/api/*/users/alarm/subscribe/*").permitAll()
            .antMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .logout().logoutSuccessUrl("/").and()
            .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().regexMatchers("^(?!/api/).*"));
    }

}
