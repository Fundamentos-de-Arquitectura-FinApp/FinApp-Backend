package com.example.finappapirest.finances.domain.model.commands.store;

public record CreateStoreCommand(
         String ruc,
         String name,
         String phone,
         String address,
         String photo
) {

}
