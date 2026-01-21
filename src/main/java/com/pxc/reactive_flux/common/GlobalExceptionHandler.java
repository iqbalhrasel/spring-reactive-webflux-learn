package com.pxc.reactive_flux.common;

import com.pxc.reactive_flux.categories.CategoryNotFoundException;
import com.pxc.reactive_flux.products.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class GlobalExceptionHandler {
    public HandlerFilterFunction<ServerResponse, ServerResponse> handleErrors() {
        return (request, next) ->
                next.handle(request)
                        .onErrorResume(CategoryNotFoundException.class,
                                e -> ServerResponse
                                        .status(HttpStatus.NOT_FOUND)
                                        .bodyValue(new ErrorDto("Category not found"))
                        )
                        .onErrorResume(ProductNotFoundException.class,
                                e -> ServerResponse
                                        .status(HttpStatus.NOT_FOUND)
                                        .bodyValue(new ErrorDto("Product not found"))
                        );
    }
}
