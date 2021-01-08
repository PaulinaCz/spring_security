package com.czerniecka.spring_security.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        // hardcoded value of token -> only for simplicity of
        // following and understanding csrf flow
        CsrfToken token = new DefaultCsrfToken("X-CSRF_TOKEN", "_csrf", "123456789");
        return token;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        return null;
    }
}
