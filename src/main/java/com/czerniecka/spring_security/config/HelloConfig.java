package com.czerniecka.spring_security.config;

import com.czerniecka.spring_security.services.JPAUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class HelloConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new JPAUserDetailsService();
    }

    // Password encoder return plain text -
    // it is used only for training purposes
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
