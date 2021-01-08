package com.czerniecka.spring_security.security.filters;

import com.czerniecka.spring_security.authentications.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = request.getHeader("Authorization");

        Authentication auth = new TokenAuthentication(token, null);

        // if valid authentication -> set SecurityContextHolder
        // if not -> throws Exception and doesn't proceed to code below var a
        var a = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(a);
        filterChain.doFilter(request, response);

    }

    // Acts for all endpoints apart from login
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
