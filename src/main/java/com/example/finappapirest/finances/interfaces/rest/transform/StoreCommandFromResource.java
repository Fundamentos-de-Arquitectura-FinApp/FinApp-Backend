package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.commands.store.CreateStoreCommand;
import com.example.finappapirest.finances.domain.model.commands.store.UpdateStoreCommand;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateStoreRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateStoreRequest;

public class StoreCommandFromResource {
    public static CreateStoreCommand fromResource(CreateStoreRequest request) {
        return new CreateStoreCommand(
                request.ruc(),
                request.name(),
                request.phone(),
                request.address(),
                request.photo()
        );
    }

    public static UpdateStoreCommand fromResource(UpdateStoreRequest request) {
        return new UpdateStoreCommand(
                request.id(),
                request.ruc(),
                request.name(),
                request.phone(),
                request.address(),
                request.photo()
        );
    }
}
