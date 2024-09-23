package com.example.finappapirest.inventory.domain.services.commands;

import com.example.finappapirest.inventory.domain.model.commands.CreateProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.DeleteProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.UpdateProductCommand;
import com.example.finappapirest.inventory.domain.model.entities.Product;

public interface ProductsCommandService {
    Product handle(CreateProductCommand command);
    Product handle(UpdateProductCommand command);
    Boolean handle(DeleteProductCommand command);
}
