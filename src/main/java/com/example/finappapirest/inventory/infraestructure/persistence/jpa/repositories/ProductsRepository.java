package com.example.finappapirest.inventory.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.inventory.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long> {
    List<Product> findByStoreId(Long storeId);
}
