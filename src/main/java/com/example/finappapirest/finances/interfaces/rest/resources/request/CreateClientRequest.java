package com.example.finappapirest.finances.interfaces.rest.resources.request;

public record CreateClientRequest(
        String email,
        String names,
        String paternalSurname,
        String maternalSurname,
        String dni,
        String phone,
        String photo
) {

}
