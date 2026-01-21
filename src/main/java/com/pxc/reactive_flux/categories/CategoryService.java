package com.pxc.reactive_flux.categories;

import com.pxc.reactive_flux.products.ProductCrudRepository;
import com.pxc.reactive_flux.products.ProductDto;
import com.pxc.reactive_flux.products.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductCrudRepository productCrudRepository;
    private final ProductMapper productMapper;

    public Mono<CategoryDto> createCategory(CategoryAddRequest request) {
        var category = new Category();
        category.setName(request.getName());

        return categoryRepository
                .save(category)
                .map(c -> new CategoryDto(c.getId(), c.getName()));
    }

    public Mono<CategoryDto> updateCategory(Integer id, CategoryUpdateRequest request) {
        return categoryRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException()))
                .flatMap(category -> {
                    category.setName(request.getName());
                    return categoryRepository.save(category);
                })
                .map(c -> new CategoryDto(c.getId(), c.getName()));
    }

    public Flux<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .map(c -> new CategoryDto(c.getId(), c.getName()));
    }

    public Mono<CategoryDto> getCategory(Integer id) {
        return categoryRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException()))
                .map(c -> new CategoryDto(c.getId(), c.getName()));
    }

    public Mono<CategoryWithProductsDto> getCategoryWithProducts(Integer id) {
        var categoryMono = categoryRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException()));

        var productsMono = productCrudRepository
                .findByCategoryId(id)
                .map(p -> productMapper.toDto(p))
                .collectList();

        return Mono
                .zip(categoryMono, productsMono)
                .map(tuple -> {
                    var category = tuple.getT1();
                    var products = tuple.getT2();

                    return new CategoryWithProductsDto(category.getId(), category.getName(), products);
                });
    }
}
