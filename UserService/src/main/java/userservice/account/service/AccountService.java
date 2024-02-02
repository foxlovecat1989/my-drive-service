package userservice.account.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import userservice.account.domain.Account;
import userservice.account.domain.AccountState;
import userservice.account.repo.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository repository;

    public Optional<Account> getAccount(UUID userId) {
        return repository.findByUserId(userId);
    }

    public Account createAccount(UUID userId) {
        log.info("Create a account with UserId: {}", userId);
        Account newAccount = Account.builder()
                .accountId(UUID.randomUUID())
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .state(AccountState.NORMAL)
                .build();
        log.info("Create a account: {}", newAccount);

        return repository.save(newAccount);
    }
}
