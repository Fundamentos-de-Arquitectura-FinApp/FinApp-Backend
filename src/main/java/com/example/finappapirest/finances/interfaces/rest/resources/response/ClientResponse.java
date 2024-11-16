package com.example.finappapirest.finances.interfaces.rest.resources.response;

public record ClientResponse(
        Long id,
        String names,
        String paternalSurname,
        String maternalSurname,
        String dni,
        String phone,
        String photo,
        String email,
        String accountId
) {
    public ClientResponse(Long id, String names, String paternalSurname, String maternalSurname, String dni, String phone, String photo) {
        this(id, names, paternalSurname, maternalSurname, dni, phone, photo, "", ""); // accountId por defecto vacío
    }
}
