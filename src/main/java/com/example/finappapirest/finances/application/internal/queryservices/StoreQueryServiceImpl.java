package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.queries.store.ExistStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetAllStoresQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.queries.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.StoreRepository;
import com.example.finappapirest.shared.domain.model.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    @Override
    public List<Store> handle(GetAllStoresQuery query) {
        return storeRepository.findAll();
    }

    @Override
    public Store handle(GetStoreByUserIdQuery query) {
        return storeRepository.findByUserId(query.userId()).orElseThrow(()->new NotFoundException("Store not found"));
    }

    @Override
    public Store handle(GetStoreByIdQuery query) {
        return storeRepository.findById(query.storeId()).orElseThrow(()->new NotFoundException("Store not found"));

    }

    @Override
    public boolean handle(ExistStoreByUserIdQuery query) {
        Optional<Store> store = storeRepository.findByUserId(query.userId());
        return store.isPresent();
    }
}
