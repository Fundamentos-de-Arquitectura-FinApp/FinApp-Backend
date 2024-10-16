package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.domain.model.queries.client.GetAllClientsQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByDniQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientsByStoreQuery;

import java.util.List;

public interface ClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
    Client handle(GetClientByIdQuery query);
    Client handle(GetClientByDniQuery query);
    List<Client> handle(GetClientsByStoreQuery query);
}
