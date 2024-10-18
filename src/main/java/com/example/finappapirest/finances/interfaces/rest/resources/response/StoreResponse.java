package com.example.finappapirest.finances.interfaces.rest.resources.response;

public record StoreResponse(
        Long id,
        String ruc,
        String name,
        String phone,
        String address,
        String photo
) {
}
