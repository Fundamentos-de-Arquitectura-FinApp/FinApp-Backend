package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.interfaces.rest.resources.response.ClientResponse;

public class ClientResourceFromEntity {
    public static ClientResponse fromEntity(Client client,String email) {
        return new ClientResponse(
                client.getId(),
                client.getNames(),
                client.getPaternalSurname(),
                client.getMaternalSurname(),
                client.getDni(),
                client.getPhone(),
                client.getPhoto(),
                email,
                client.getAccount()==null? "":client.getAccount().getId().toString()
        );
    }
    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getNames(),
                client.getPaternalSurname(),
                client.getMaternalSurname(),
                client.getDni(),
                client.getPhone(),
                client.getPhoto()
        );
    }


}
