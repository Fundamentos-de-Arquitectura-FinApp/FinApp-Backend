package com.example.finappapirest.finances.interfaces.rest.resources.request;

public record CreateStoreRequest(
        String ruc,
        String name,
        String phone,
        String address,
        String photo
) {
}
