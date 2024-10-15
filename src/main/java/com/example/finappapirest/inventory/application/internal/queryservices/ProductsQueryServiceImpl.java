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

@AllArgsConstructor
@Service
public class ProductsQueryServiceImpl implements ProductsQueryService {
    private final ProductsRepository productsRepository;

    @Override
    public List<Product> handle(GetAllProductsByStoreQuery query) {
        return this.productsRepository.findByStoreId(query.storeId());
    }

    @Override
    public Product handle(GetProductByIdQuery query) {
        return this.productsRepository.findById(query.productId()).orElseThrow(()->new BadRequestException("Product not found"));
    }

    @Override
    public boolean handle(ExistsProductById query) {
        return this.productsRepository.existsById(query.productId());
    }
}
