package com.pxc.reactive_flux.categories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                                .POST("", handler::createCategory))
                .build();
    }
}
