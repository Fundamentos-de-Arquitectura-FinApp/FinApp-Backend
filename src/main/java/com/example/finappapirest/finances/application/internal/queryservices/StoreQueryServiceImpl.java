package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.queries.GetAllStoresQuery;
import com.example.finappapirest.finances.domain.model.queries.GetStoreByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.GetStoreByUserIdQuery;
import com.example.finappapirest.finances.domain.services.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return storeRepository.findByUserId(query.userId()).orElseThrow(()->new RuntimeException("Store not found"));
    }

    @Override
    public Store handle(GetStoreByIdQuery query) {
        return storeRepository.findById(query.storeId()).orElseThrow(()->new RuntimeException("Store not found"));

    }
}
