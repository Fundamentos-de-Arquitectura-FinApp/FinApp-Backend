package com.example.finappapirest.finances.domain.services;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.domain.model.commands.client.CreateClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.DeleteClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.UpdateClientCommand;

public interface ClientCommandService {
    Client handle(CreateClientCommand command);
    Client handle(UpdateClientCommand command);
    void handle(DeleteClientCommand command);
}
