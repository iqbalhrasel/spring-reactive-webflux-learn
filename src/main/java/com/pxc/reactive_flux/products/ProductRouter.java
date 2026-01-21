package com.pxc.reactive_flux.products;

import com.pxc.reactive_flux.common.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@AllArgsConstructor
@Configuration
public class ProductRouter {
    private final GlobalExceptionHandler exceptionHandler;

    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductHandler handler){
        return RouterFunctions
                .route()
                .path("/products", b -> b
                        .GET("", handler::getAllProducts)
                        .GET("/{id}", handler::getProduct)
                        .POST("", handler::createProduct)
                        .PUT("/{id}", handler::updateProduct)
                        .DELETE("/{id}", handler::deleteProduct)
                )
                .filter(exceptionHandler.handleErrors())
                .build();
    }
}
