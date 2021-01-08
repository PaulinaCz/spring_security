package com.czerniecka.spring_security.security.filters;

import com.czerniecka.spring_security.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter implements Filter {

    @Autowired
    private AuthenticationManager manager;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String authorization = httpRequest.getHeader("Authorization");

        // should directly implement authentication contract
        // for correct implementation !

        var a = new CustomAuthentication(authorization, null);

        try{
            Authentication result = manager.authenticate(a);

            if(result.isAuthenticated()){ // for correct implementation of Authentication will always be true
                SecurityContextHolder.getContext().setAuthentication(result);
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (AuthenticationException e){
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}
