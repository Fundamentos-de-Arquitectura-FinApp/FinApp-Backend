package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import com.example.finappapirest.finances.interfaces.rest.resources.response.StoreResponse;

public class StoreResourceFromEntity {

    public static StoreResponse entityToResponse(Store store, String email) {
        return new StoreResponse(
            store.getId(),
            store.getRuc(),
            store.getName(),
            store.getPhone(),
            store.getAddress(),
            store.getPhoto(),
            email
        );
    }
    public static StoreResponse entityToResponse(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getRuc(),
                store.getName(),
                store.getPhone(),
                store.getAddress(),
                store.getPhoto()
        );
    }
}
