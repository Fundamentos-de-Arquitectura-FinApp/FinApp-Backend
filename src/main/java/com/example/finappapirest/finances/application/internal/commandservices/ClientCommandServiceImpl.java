package com.example.finappapirest.finances.application.internal.commandservices;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.domain.model.commands.client.CreateClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.DeleteClientCommand;
import com.example.finappapirest.finances.domain.model.commands.client.UpdateClientCommand;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.commands.ClientCommandService;
import com.example.finappapirest.finances.domain.services.queries.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.ClientRepository;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.StoreRepository;
import com.example.finappapirest.notifications.interfaces.acl.NotificationServiceFacade;
import com.example.finappapirest.security.domain.model.valueobjects.Roles;
import com.example.finappapirest.security.interfaces.acl.UserServiceFacade;
import com.example.finappapirest.shared.domain.model.exceptions.NotFoundException;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;
    private final StoreRepository storeRepository;
    private final UserServiceFacade userServiceFacade;
    private final StoreQueryService storeQueryService;
    private final NotificationServiceFacade notificationServiceFacade;

    @Override
    public Client handle(CreateClientCommand command) {

        var storeId = UserUtils.getCurrentUserId();
        var store = storeQueryService.handle(new GetStoreByUserIdQuery(storeId));

        Client client = new Client();

        client.setStore(store);
        client.setNames(command.names());
        client.setPaternalSurname(command.paternalSurname());
        client.setMaternalSurname(command.maternalSurname());
        client.setDni(command.dni());
        client.setPhone(command.phone());
        client.setPhoto(command.photo());

        Long userId = userServiceFacade.createUser(command.email(), command.dni(), List.of(Roles.ROLE_CLIENT));

        client.setUserId(userId);

        store.addClient(client);
        storeRepository.save(store);

        notificationServiceFacade.sendNotification(userId, "Bienvenido a la tienda " + store.getName());
        notificationServiceFacade.sendNotification(storeId, "Se ha registrado un nuevo cliente con nombre " + command.names());
        return client;
    }

    @Override
    public Client handle(UpdateClientCommand command) {
        var client = clientRepository.findById(command.id()).orElseThrow(() -> new NotFoundException("Client not found"));
        client.setPhone(command.phone());
        client.setPhoto(command.photo());
        return clientRepository.save(client);
    }

    @Override
    public void handle(DeleteClientCommand command) {
        var client = clientRepository.findById(command.clientId()).orElseThrow(() -> new NotFoundException("Client not found"));
        client.setActive(false);
        clientRepository.save(client);
    }
}
