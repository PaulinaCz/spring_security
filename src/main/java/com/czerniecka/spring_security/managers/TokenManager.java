package com.czerniecka.spring_security.managers;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    //store all provided tokens
    private Set<String> tokens = new HashSet<>();

    public void add(String token){
        tokens.add(token);
    }

    public boolean contains(String token){
        return  tokens.contains(token);
    }

}
