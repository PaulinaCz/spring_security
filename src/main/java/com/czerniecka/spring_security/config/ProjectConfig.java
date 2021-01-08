package com.czerniecka.spring_security.config;

import com.czerniecka.spring_security.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.csrf().disable(); // can disclose app to malicious software

        http.csrf(
                c -> {
                    c.ignoringAntMatchers("/csrfdisabled/**"); //case example when csrf disabled
                    c.csrfTokenRepository(new CustomCsrfTokenRepository());
                }
        );
    }
}
