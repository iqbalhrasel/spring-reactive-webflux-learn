package com.pxc.reactive_flux.categories;

import com.pxc.reactive_flux.common.ErrorDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CategoryRouter {

    @Bean
    public RouterFunction<ServerResponse> categoryRoutes(CategoryHandler handler){
        return RouterFunctions
                .route()
                .path("/categories",
                        b -> b
                                .GET("", handler::getAllCategories)
                                .GET("/{id}", handler::getCategory)
                                .GET("/{id}/products", handler::getCategoryWithProducts)
                                .POST("", handler::createCategory)
                                .PUT("/{id}", handler::updateCategory))
                .filter(handleErrors())
                .build();
    }

    public HandlerFilterFunction<ServerResponse, ServerResponse> handleErrors() {
        return (request, next) ->
                next.handle(request)
                        .onErrorResume(CategoryNotFoundException.class,
                                e -> ServerResponse
                                                .status(HttpStatus.NOT_FOUND)
                                                .bodyValue(new ErrorDto("Category not found"))
                        );
    }
}
