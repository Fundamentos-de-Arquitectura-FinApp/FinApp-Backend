package com.example.finappapirest.finances.interfaces.rest.resources.response;

public record ClientResponse(
        Long id,
        String names,
        String paternalSurname,
        String maternalSurname,
        String dni,
        String phone,
        String photo
) {
}
