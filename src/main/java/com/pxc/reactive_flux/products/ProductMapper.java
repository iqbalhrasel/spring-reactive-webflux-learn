package com.pxc.reactive_flux.products;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductAddRequest request) {
        if(request == null)
            return null;

        var product = new Product();
        product.setCategoryId(request.getCategoryId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return product;
    }

    public ProductDto toDto(Product product, Integer id) {
        var dto = new ProductDto();
        dto.setCategoryId(product.getCategoryId());
        dto.setId(id);
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }

    public ProductDto toDto(Product product) {
        var dto = new ProductDto();
        dto.setCategoryId(product.getCategoryId());
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }
}
