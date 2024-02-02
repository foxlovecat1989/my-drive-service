package userservice.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import userservice.account.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(UUID clientId);
}
