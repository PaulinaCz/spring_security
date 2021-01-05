package com.czerniecka.spring_security.services;

import com.czerniecka.spring_security.entities.User;
import com.czerniecka.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        User u = user.orElseThrow(() -> new UsernameNotFoundException("Error! Not Found!"));
        return new SecurityUser(u);
    }
}
