package com.czerniecka.spring_security.config;

import com.czerniecka.spring_security.security.JpaClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(cds());
    }

    /*
    Below -> basic implementation of nonopaque token
    Most used (and probably only implementation at the moment!)
    of nonopaque token - JWT - Json Web Token
    (actually - B-64 encoding of JSON in String representation)
    JWT carries encoded data split in 3 parts separated with "."
    HEADER -> alg/token_type/key_id
    BODY (PAYLOAD) -> user_credentials/authorities/scope/expiration/client(who required token)/etc
    SIGNATURE -> tokens are being signed so nobody is able to change data they transfer
    * */

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(converter());
    }

    @Bean
    public JwtAccessTokenConverter converter(){
        var c = new JwtAccessTokenConverter();
        c.setSigningKey("secret"); // "password" for authorization server uses to sign the tokens
        return c;
    }

    @Bean
    public JpaClientDetailsService cds(){
        return new JpaClientDetailsService();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                    .tokenStore(tokenStore())
                    .accessTokenConverter(converter());
    }
}
