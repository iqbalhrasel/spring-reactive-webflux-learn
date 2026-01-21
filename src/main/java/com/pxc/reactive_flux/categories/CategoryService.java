package com.pxc.reactive_flux.categories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Mono<CategoryDto> createCategory(CategoryAddRequest request) {
        var category = new Category();
        category.setName(request.getName());

        return categoryRepository
                .save(category)
                .map(c -> new CategoryDto(c.getId(), c.getName()));
    }
}
