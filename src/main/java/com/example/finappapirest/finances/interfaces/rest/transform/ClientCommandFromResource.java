package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.commands.client.CreateClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.UpdateClientCommand;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateClientRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateClienteRequest;

public class ClientCommandFromResource {

    public static UpdateClientCommand fromResource(UpdateClienteRequest request, Long clientId){
        return new UpdateClientCommand(clientId,  request.phone(), request.photo());
    }

    public static CreateClientCommand fromResource(CreateClientRequest request){
        return new CreateClientCommand(
                request.email(),
                request.names(),
                request.paternalSurname(),
                request.maternalSurname(),
                request.dni(),
                request.phone(),
                request.photo()
        );
    }
}
