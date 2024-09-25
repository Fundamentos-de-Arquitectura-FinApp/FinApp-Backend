package com.example.finappapirest.inventory.interfaces.acl;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.domain.model.queries.GetProductByIdQuery;
import com.example.finappapirest.inventory.domain.services.queries.ProductsQueryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceFacade {
    private final ProductsQueryService productQueryService;

    public Product getProductById(Long productId) {
        var query = new GetProductByIdQuery(productId);
        return productQueryService.handle(query);
    }
}
