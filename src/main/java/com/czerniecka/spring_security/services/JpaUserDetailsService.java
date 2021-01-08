package com.czerniecka.spring_security.services;

import com.czerniecka.spring_security.entities.User;
import com.czerniecka.spring_security.repositories.UserRepository;
import com.czerniecka.spring_security.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userO = userRepository.findUserByUsername(username);
        User user = userO.orElseThrow(() -> new UsernameNotFoundException("Not found!"));
        return new SecurityUser(user);
    }
}
