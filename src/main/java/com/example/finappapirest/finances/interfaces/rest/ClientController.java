package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.commands.client.CreateClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.DeleteClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.UpdateClientCommand;
import com.example.finappapirest.finances.domain.model.queries.client.GetAllClientsQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientsByStoreQuery;
import com.example.finappapirest.finances.domain.services.ClientCommandService;
import com.example.finappapirest.finances.domain.services.ClientQueryService;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateClientRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateClienteRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.response.ClientResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.ClientCommandFromResource;
import com.example.finappapirest.finances.interfaces.rest.transform.ClientResourceFromEntity;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController()
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
@Tag(name = "Clients", description = "Clients management")
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    @GetMapping()
    @Operation(summary = "Get all clients", description = "Get all clients from the system")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        var query = new GetAllClientsQuery();
        var clients = clientQueryService.handle(query);
        var clientsResponse = clients.stream().map(ClientResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(clientsResponse);
    }

    @GetMapping("/store")
    @Operation(summary = "Get clients by store", description = "Get all clients from the system by store")
    public ResponseEntity<List<ClientResponse>> getClientsByStore() {
        Long userId = UserUtils.getCurrentUserId();
        var query = new GetClientsByStoreQuery(userId);
        var clients = clientQueryService.handle(query);
        var clientsResponse = clients.stream().map(ClientResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(clientsResponse);
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get client by id", description = "Get client by id from the system")
    public ResponseEntity<ClientResponse> getClient(@PathVariable("clientId") Long clientId) {
        var query = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(query);
        var clientResponse = ClientResourceFromEntity.fromEntity(client);
        return ResponseEntity.ok(clientResponse);
    }

    @PostMapping()
    @Operation(summary = "Create client", description = "Create client in the system")
    public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request) {
        CreateClientCommand command = ClientCommandFromResource.fromResource(request);
        var client = clientCommandService.handle(command);
        var clientResponse = ClientResourceFromEntity.fromEntity(client);
        return new ResponseEntity<>(clientResponse,HttpStatus.CREATED);
    }

    @PatchMapping("/{clientId}")
    @Operation(summary = "Update client", description = "Update client in the system")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable("clientId") Long clientId, @RequestBody UpdateClienteRequest request) {
        UpdateClientCommand command = ClientCommandFromResource.fromResource(request,clientId);
        var client = clientCommandService.handle(command);
        var clientResponse = ClientResourceFromEntity.fromEntity(client);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{clientId}")
    @Operation(summary = "Delete client", description = "Delete client in the system")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long clientId) {
        var command = new DeleteClientCommand(clientId);
        clientCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
