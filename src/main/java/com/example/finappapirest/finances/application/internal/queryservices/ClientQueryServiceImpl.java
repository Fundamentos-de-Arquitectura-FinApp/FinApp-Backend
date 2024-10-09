package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.queries.client.GetAllClientsQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByDniQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientsByStoreQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.ClientQueryService;
import com.example.finappapirest.finances.domain.services.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.ClientRepository;
import com.example.finappapirest.shared.domain.model.exceptions.NotFoundException;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;
    private final StoreQueryService storeQueryService;

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clientRepository.findAll();
    }

    @Override
    public Client handle(GetClientByIdQuery query) {
        return clientRepository.findById(query.clientId()).orElseThrow(()->new NotFoundException(
                String.format("Client with id %s not found", query.clientId())
        ));
    }

    @Override
    public Client handle(GetClientByDniQuery query) {
        return clientRepository.findByDni(query.dni()).orElseThrow(()->new NotFoundException(
                String.format("Client with dni %s not found", query.dni())
        ));
    }

    @Override
    public List<Client> handle(GetClientsByStoreQuery query) {
         Long userId = UserUtils.getCurrentUserId();
         Store store = storeQueryService.handle(new GetStoreByUserIdQuery(userId));
         return clientRepository.findByStoreId(store.getId());
    }
}
