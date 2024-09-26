package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/clients")
@Tag(name = "Clients", description = "Clients management")
public class ClientController {

    @GetMapping()
    @Operation(summary = "Get all clients", description = "Get all clients from the system")
    public String getAllClients() {
        return "Clients";
    }

    @GetMapping("/store")
    @Operation(summary = "Get clients by store", description = "Get all clients from the system by store")
    public String getClientsByStore() {
        return "Clients by store";
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get client by id", description = "Get client by id from the system")
    public String getClient(@PathVariable("clientId") Long clientId) {
        return "Client " + clientId;
    }

    @PostMapping()
    @Operation(summary = "Create client", description = "Create client in the system")
    public String createClient() {
        return "Client created";
    }

    @PutMapping("/{clientId}")
    @Operation(summary = "Update client", description = "Update client in the system")
    public String updateClient(@PathVariable("clientId") Long clientId) {
        return "Client updated";
    }

    @DeleteMapping("/{clientId}")
    @Operation(summary = "Delete client", description = "Delete client in the system")
    public String deleteClient(@PathVariable("clientId") Long clientId) {
        return "Client deleted";
    }

}
