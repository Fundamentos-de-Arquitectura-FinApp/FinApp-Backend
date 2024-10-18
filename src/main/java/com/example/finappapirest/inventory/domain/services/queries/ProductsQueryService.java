package com.example.finappapirest.inventory.domain.services.queries;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import com.example.finappapirest.inventory.domain.model.queries.ExistsProductById;
import com.example.finappapirest.inventory.domain.model.queries.GetAllProductsByStoreQuery;
import com.example.finappapirest.inventory.domain.model.queries.GetProductByIdQuery;

import java.util.List;

public interface ProductsQueryService {
    List<Product> handle(GetAllProductsByStoreQuery query);
    Product handle(GetProductByIdQuery query);
    boolean handle(ExistsProductById query);
}
