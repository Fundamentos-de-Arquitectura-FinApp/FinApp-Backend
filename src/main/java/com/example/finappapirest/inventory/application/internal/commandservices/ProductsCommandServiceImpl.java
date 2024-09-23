package com.example.finappapirest.inventory.application.internal.commandservices;

import com.example.finappapirest.inventory.domain.model.commands.CreateProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.DeleteProductCommand;
import com.example.finappapirest.inventory.domain.model.commands.UpdateProductCommand;
import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.domain.model.queries.GetProductByIdQuery;
import com.example.finappapirest.inventory.domain.services.commands.ProductsCommandService;
import com.example.finappapirest.inventory.domain.services.queries.ProductsQueryService;
import com.example.finappapirest.inventory.infraestructure.persistence.jpa.repositories.ProductsRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsCommandServiceImpl implements ProductsCommandService {
    private final ProductsRepository productsRepository;
    private final ProductsQueryService productsQueryService;

    @Override
    public Product handle(CreateProductCommand command) {
        Product product = Product.builder()
                .name(command.name())
                .description(command.description())
                .isActive(true)
                .storeId(command.storeId())
                .build();
        this.productsRepository.save(product);
        return product;
    }

    @Override
    public Product handle(UpdateProductCommand command) {
        var query = new GetProductByIdQuery(command.productId());
        Product product = this.productsQueryService.handle(query);
        product.setName(command.name());
        product.setDescription(command.description());
        product.setPrice(command.price());
        this.productsRepository.save(product);
        return product;
    }

    @Override
    public Boolean handle(DeleteProductCommand command) {
        var query = new GetProductByIdQuery(command.productId());
        Product product = this.productsQueryService.handle(query);
        product.setIsActive(false);
        this.productsRepository.save(product);
        return true;
    }
}
