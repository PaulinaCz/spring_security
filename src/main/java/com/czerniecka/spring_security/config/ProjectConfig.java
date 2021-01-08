package com.czerniecka.spring_security.config;

import com.czerniecka.spring_security.security.filters.TokenAuthFilter;
import com.czerniecka.spring_security.security.filters.UsernamePasswordAuthFilter;
import com.czerniecka.spring_security.security.providers.OtpAuthenticationProvider;
import com.czerniecka.spring_security.security.providers.TokenAuthProvider;
import com.czerniecka.spring_security.security.providers.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthProvider authProvider;

    @Autowired
    private OtpAuthenticationProvider otpProvider;

    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider)
                .authenticationProvider(otpProvider)
                .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(tokenAuthFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UsernamePasswordAuthFilter usernamePasswordAuthFilter(){
        return new UsernamePasswordAuthFilter();
    }

    @Bean
    public TokenAuthFilter tokenAuthFilter(){
        return new TokenAuthFilter();
    }
}
