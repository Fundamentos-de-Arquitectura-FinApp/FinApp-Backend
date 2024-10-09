package com.example.finappapirest.finances.interfaces.rest.resources.request;

public record UpdateClienteRequest(
        Long id,
        String phone,
        String photo
) {
}
