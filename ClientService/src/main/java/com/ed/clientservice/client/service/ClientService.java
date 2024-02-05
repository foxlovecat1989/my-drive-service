package com.ed.clientservice.client.service;

import com.ed.clientservice.client.repo.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import com.ed.clientservice.account.domain.Account;
import com.ed.clientservice.account.service.AccountService;
import com.ed.clientservice.client.model.entity.Client;
import com.ed.clientservice.client.model.enums.ClientState;
import com.ed.clientservice.client.model.record.ClientCreationRequest;
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

    public void createUser(ClientCreationRequest request) {
        log.info("Get request to create a client: {}", request);
        UUID clientId = UUID.randomUUID();
        Account newAccount = accountService.createAccount(clientId);
        Client newClient = Client.builder()
                .clientId(clientId)
                .accountId(newAccount.getAccountId())
                .name(request.name())
                .state(ClientState.NORMAL)
                .role(request.role())
                .build();
        log.info("Create a client: {}", newClient);

        clientRepository.save(newClient);
    }
}
