package com.example.finappapirest.inventory.application.internal.queryservices;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.domain.model.queries.ExistsProductById;
import com.example.finappapirest.inventory.domain.model.queries.GetAllProductsByStoreQuery;
import com.example.finappapirest.inventory.domain.model.queries.GetProductByIdQuery;
import com.example.finappapirest.inventory.domain.services.queries.ProductsQueryService;
import com.example.finappapirest.inventory.infraestructure.persistence.jpa.repositories.ProductsRepository;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductsQueryServiceImpl implements ProductsQueryService {
    private final ProductsRepository productsRepository;

    @Override
    public List<Product> handle(GetAllProductsByStoreQuery query) {
        return this.productsRepository.findByStoreId(query.storeId()).stream().filter(
                Product::getIsActive
        ).toList();
    }

    @Override
    public Product handle(GetProductByIdQuery query) {
        Optional<Product> product = this.productsRepository.findById(query.productId());
        if(product.isEmpty()) {
            throw new BadRequestException("Product not found");
        }
        if(product.get().getIsActive()) {
            return product.get();
        }
        throw new BadRequestException("Product was deleted, so it is not available");
    }

    @Override
    public boolean handle(ExistsProductById query) {
        Optional<Product> product = this.productsRepository.findById(query.productId());
        if(product.isEmpty()) {
            return false;
        }
        return product.get().getIsActive();
    }
}
