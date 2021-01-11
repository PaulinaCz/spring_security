package com.czerniecka.spring_security.repositories;

import com.czerniecka.spring_security.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findClientByClientId(String clientId);
}
