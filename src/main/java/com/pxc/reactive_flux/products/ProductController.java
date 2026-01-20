package com.pxc.reactive_flux.products;

import com.pxc.reactive_flux.categories.CategoryNotFoundException;
import com.pxc.reactive_flux.common.ErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> createProduct(@RequestBody ProductAddRequest request){
        return productService
                .createProduct(request)
                .map(dto -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(dto)
                );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public Mono<ResponseEntity<ErrorDto>> handleCategoryNotFound() {
        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDto("Category not found"))
        );
    }
}
