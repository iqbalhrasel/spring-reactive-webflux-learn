package com.pxc.reactive_flux.products;

import com.pxc.reactive_flux.categories.CategoryNotFoundException;
import com.pxc.reactive_flux.categories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductDbRepository productDbRepository;
    private final ProductCrudRepository crudRepository;
    private final ProductMapper productMapper;

    public Mono<ProductDto> createProduct(ProductAddRequest request){
        return categoryRepository
                .existsById(request.getCategoryId())
                .flatMap(exists -> {
                    if(exists){
                        Product product = productMapper.toEntity(request);
                        return crudRepository.save(product).map(productMapper::toDto);
                    }
                    else throw new CategoryNotFoundException();
                });
    }

    public Flux<ProductDto> getAllProducts() {
        return crudRepository
                .findAll()
                .map(p -> productMapper.toDto(p));
    }

    public Mono<ProductDto> getProduct(Integer id) {
        return crudRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException()))
                .map(p -> productMapper.toDto(p));
    }
}
