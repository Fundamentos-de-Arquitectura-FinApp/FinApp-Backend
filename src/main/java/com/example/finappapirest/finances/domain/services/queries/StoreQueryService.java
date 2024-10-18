package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.queries.store.GetAllStoresQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.store.GetStoreByUserIdQuery;

import java.util.List;

public interface StoreQueryService {
    List<Store> handle(GetAllStoresQuery query);
    Store handle(GetStoreByUserIdQuery query);
    Store handle(GetStoreByIdQuery query);
}
