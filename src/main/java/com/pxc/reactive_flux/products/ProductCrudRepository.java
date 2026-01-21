package com.pxc.reactive_flux.products;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductCrudRepository extends ReactiveCrudRepository<Product, Integer> {

    Flux<Product> findByCategoryId(Integer categoryId);
}
