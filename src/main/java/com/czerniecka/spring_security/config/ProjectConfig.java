package com.czerniecka.spring_security.config;

import com.czerniecka.spring_security.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        // Create in memory user for testing
        var u = User.withUsername("John")
                .password("John123")
                .authorities("read")
                .build();

        uds.createUser(u);

        return uds;
    }

    // not hashed password only for testing purposes!
    // as UserDetailsManager requires passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        // can add as many authenticationProviders as application requires
        auth.authenticationProvider(authenticationProvider);
    }
}
