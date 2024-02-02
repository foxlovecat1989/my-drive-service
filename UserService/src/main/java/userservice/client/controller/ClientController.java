package userservice.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import userservice.client.domain.Client;
import userservice.client.record.ClientQueryResponse;
import userservice.client.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/client")
@AllArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService service;

    @GetMapping("{clientId}")
    public ResponseEntity<ClientQueryResponse> getClientInfo(@PathVariable String clientId) {
        log.info("driverId: {}", clientId);
        Optional<Client> clientOpt = service.getClient(UUID.fromString(clientId));

        if (clientOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client client = clientOpt.get();
        ClientQueryResponse response = new ClientQueryResponse(
                client.getClientId(), client.getName(), client.getAccountId());


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
