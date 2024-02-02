package userservice.client.service;

import lombok.extern.slf4j.Slf4j;
import userservice.account.domain.Account;
import userservice.account.service.AccountService;
import userservice.client.domain.Client;
import userservice.client.domain.ClientState;
import userservice.client.record.ClientCreationRequest;
import userservice.client.repo.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final AccountService accountService;

    public Optional<Client> getClient(UUID clientId) {
        return clientRepository.findByClientId(clientId);
    }

    public void createClient(ClientCreationRequest request) {
        log.info("Get request to create a client: {}", request);
        UUID userId = UUID.randomUUID();
        Account newAccount = accountService.createAccount(userId);
        Client newClient = Client.builder()
                .clientId(userId)
                .accountId(newAccount.getAccountId())
                .name(request.name())
                .state(ClientState.NORMAL)
                .build();
        log.info("Create a client: {}", newClient);

        clientRepository.save(newClient);
    }
}
