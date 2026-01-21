package com.pxc.reactive_flux.categories;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class CategoryHandler {
    private final CategoryService categoryService;

    public Mono<ServerResponse> createCategory(ServerRequest serverRequest){
        return serverRequest
                .bodyToMono(CategoryAddRequest.class)
                .flatMap(request ->
                    categoryService
                            .createCategory(request)
                            .flatMap(dto ->
                                    ServerResponse.status(HttpStatus.CREATED).bodyValue(dto)));
    }

    public Mono<ServerResponse> updateCategory(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return serverRequest
                .bodyToMono(CategoryUpdateRequest.class)
                .flatMap(request ->
                        categoryService
                                .updateCategory(id, request)
                                .flatMap(dto ->
                                        ServerResponse.status(HttpStatus.CREATED).bodyValue(dto)));
    }

    public Mono<ServerResponse> getAllCategories(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(categoryService.getAllCategories(), CategoryDto.class);
    }

    public Mono<ServerResponse> getCategory(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return categoryService
                .getCategory(id)
                .flatMap(dto -> ServerResponse.ok().bodyValue(dto));
    }

    public Mono<ServerResponse> getCategoryWithProducts(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse
                .ok()
                .body(categoryService.getCategoryWithProducts(id), CategoryWithProductsDto.class);
    }

    public Mono<ServerResponse> deleteCategory(ServerRequest serverRequest) {
        var id = Integer.valueOf(serverRequest.pathVariable("id"));
        return categoryService
                .deleteCategory(id)
                .then(ServerResponse.noContent().build());
    }
}
