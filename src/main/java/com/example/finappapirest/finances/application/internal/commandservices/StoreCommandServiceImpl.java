package com.example.finappapirest.finances.application.internal.commandservices;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.commands.CreateStoreCommand;
import com.example.finappapirest.finances.domain.model.commands.UpdateStoreCommand;
import com.example.finappapirest.finances.domain.model.queries.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.StoreCommandService;
import com.example.finappapirest.finances.domain.services.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.StoreRepository;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final StoreQueryService storeQueryService;

    @Override
    public Store handle(CreateStoreCommand command) {
        Long userId = UserUtils.getCurrentUserId();
        Store store = Store.builder()
                .ruc(command.ruc())
                .name(command.name())
                .phone(command.phone())
                .address(command.address())
                .photo(command.photo())
                .userId(userId).build();
        return storeRepository.save(store);
    }

    @Override
    public Store handle(UpdateStoreCommand command) {

        Long userId = UserUtils.getCurrentUserId();

        var query = new GetStoreByUserIdQuery(userId);
        Store store = storeQueryService.handle(query);

        store.setRuc(command.ruc());
        store.setName(command.name());
        store.setPhone(command.phone());
        store.setAddress(command.address());
        store.setPhoto(command.photo());
        return storeRepository.save(store);
    }
}
