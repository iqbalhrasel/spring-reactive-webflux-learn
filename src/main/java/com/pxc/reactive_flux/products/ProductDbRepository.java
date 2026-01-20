package com.pxc.reactive_flux.products;

import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class ProductDbRepository {
    private final DatabaseClient databaseClient;

    public Mono<Integer> saveProduct(Product product){
        return databaseClient.sql("INSERT INTO products (category_id, name, price) VALUES (:category_id, :name, :price)")
                .bind("category_id", product.getCategoryId())
                .bind("name", product.getName())
                .bind("price", product.getPrice())
                .fetch()
                .first()
                .map(row -> (Integer) row.get("id"));
    }
}
