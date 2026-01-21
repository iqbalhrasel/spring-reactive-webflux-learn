package com.pxc.reactive_flux.products;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class ProductHandler {
    private final ProductService productService;

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(ProductAddRequest.class)
                .flatMap(request -> productService
                        .createProduct(request)
                        .flatMap(dto -> ServerResponse
                                .status(HttpStatus.CREATED)
                                .bodyValue(dto)));
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(productService.getAllProducts(), ProductDto.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return productService
                .getProduct(id)
                .flatMap(dto -> ServerResponse.ok().bodyValue(dto));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return serverRequest
                .bodyToMono(ProductUpdateRequest.class)
                .flatMap(request ->
                        productService.updateProduct(id, request)
                                .flatMap(dto -> ServerResponse.ok().bodyValue(dto))
                );
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return productService
                .deleteProduct(id)
                .then(ServerResponse.noContent().build());
    }
}
