package com.example.finappapirest.finances.domain.services;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.commands.CreateStoreCommand;
import com.example.finappapirest.finances.domain.model.commands.UpdateStoreCommand;

public interface StoreCommandService {
     Store handle (CreateStoreCommand command);
     Store handle(UpdateStoreCommand command);
}
