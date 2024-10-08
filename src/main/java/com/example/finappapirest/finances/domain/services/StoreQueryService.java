package com.example.finappapirest.finances.domain.services;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.domain.model.queries.GetAllStoresQuery;
import com.example.finappapirest.finances.domain.model.queries.GetStoreByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.GetStoreByUserIdQuery;

import java.util.List;

public interface StoreQueryService {
    List<Store> handle(GetAllStoresQuery query);
    Store handle(GetStoreByUserIdQuery query);
    Store handle(GetStoreByIdQuery query);
}
