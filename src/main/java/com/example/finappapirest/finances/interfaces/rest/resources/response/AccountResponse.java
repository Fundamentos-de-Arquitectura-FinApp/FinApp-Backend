package com.example.finappapirest.finances.interfaces.rest.resources.response;

public record AccountResponse(
        Long accountId,
        Float creditLine,
        String dni,
        String storeName,
        String storeRuc
) {
}
