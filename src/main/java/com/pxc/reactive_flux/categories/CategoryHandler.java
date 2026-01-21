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
}
