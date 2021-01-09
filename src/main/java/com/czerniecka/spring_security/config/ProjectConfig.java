package com.czerniecka.spring_security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // disable csrf
        http.authorizeRequests()
                .anyRequest()
                .permitAll(); // & remove authorization to focus on CORS functionality

        // use cors configuration over @CrossOrigin annotations
        // also -> could be configured in application.properties
        http.cors(c -> {
            CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(List.of("http://127.0.0.1:8080"));
                cc.setAllowedMethods(List.of("GET", "POST"));
                return cc;
            };
            c.configurationSource(cs);
        });
    }
}
