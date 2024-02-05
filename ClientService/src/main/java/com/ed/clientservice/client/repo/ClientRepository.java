package com.ed.clientservice.client.repo;

import com.ed.clientservice.client.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientId(UUID clientId);
}
