package com.czerniecka.spring_security.repositories;

import com.czerniecka.spring_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

}
