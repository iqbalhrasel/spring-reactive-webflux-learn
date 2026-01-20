package com.pxc.reactive_flux.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Integer id;
    private Integer categoryId;
    private String name;
    private BigDecimal price;
}
