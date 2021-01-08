package com.czerniecka.spring_security.security.providers;

import com.czerniecka.spring_security.authentications.UsernamePasswordAuthentication;
import com.czerniecka.spring_security.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if(passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthentication(username, password, user.getAuthorities());
        }
        throw new BadCredentialsException("Not authenticated");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.equals(aClass);
    }
}
