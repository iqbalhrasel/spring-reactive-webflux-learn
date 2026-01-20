package com.pxc.reactive_flux.products;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductCrudRepository extends ReactiveCrudRepository<Product, Integer> {
}
