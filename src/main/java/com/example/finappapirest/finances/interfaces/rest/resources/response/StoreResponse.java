package com.example.finappapirest.finances.interfaces.rest.resources.response;

public record StoreResponse(
        Long id,
        String ruc,
        String name,
        String phone,
        String address,
        String photo,
        String email
) {
    public StoreResponse(Long id, String ruc, String name, String phone, String address, String photo) {
        this(id, ruc, name, phone, address, photo, ""); // email por defecto vacío
    }
}
