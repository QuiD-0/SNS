package com.quid.sns.config;

import com.quid.sns.config.filter.JwtTokenFilter;
import com.quid.sns.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;
    @Value("${jwt.secret-key}")
    private String secretKey;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
            .antMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
            .antMatchers("/api/*/users/alarm/subscribe/*").permitAll()
//            .antMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .exceptionHandling()
            .and()
            .addFilterBefore(new JwtTokenFilter(userService, secretKey),
                UsernamePasswordAuthenticationFilter.class)
            .logout().logoutSuccessUrl("/").and()
            .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().regexMatchers("^(?!/api/).*"));
    }

}
