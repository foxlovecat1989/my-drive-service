package com.ed.clientservice.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ed.clientservice.account.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(UUID clientId);
}
