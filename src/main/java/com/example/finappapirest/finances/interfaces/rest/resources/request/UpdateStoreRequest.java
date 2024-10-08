package com.example.finappapirest.finances.interfaces.rest.resources.request;

public record UpdateStoreRequest(
        Long id,
        String ruc,
        String name,
        String phone,
        String address,
        String email,
        String photo
) {
}
