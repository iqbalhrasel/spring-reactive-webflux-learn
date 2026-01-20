package com.pxc.reactive_flux.categories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Integer> {
}
