package com.czerniecka.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // here implementation of authentication logic

        //if the request is authentication return fully authenticated Authentication instance

        // if the request is not authenticated throw AuthenticationException


        // the Authentication isn't supported by Authentication Provider - return null
        // or when supports(authType) returns true but cannot decide if is supported by AP
        // null means it keeps searching within AP collection for AP "matching" our authentication
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails u = userDetailsService.loadUserByUsername(username);
        if(u != null){
            if(passwordEncoder.matches(password, u.getPassword())){
                var a = new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
                return a;
            }
        }
        throw new BadCredentialsException("Error!");

    }


    //method supports is called before authenticate() method
    // in here -> we set up Username&Password (Basic Auth Filter) as the only supported authentication
    @Override
    public boolean supports(Class<?> authType) { //type of Authentication
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
