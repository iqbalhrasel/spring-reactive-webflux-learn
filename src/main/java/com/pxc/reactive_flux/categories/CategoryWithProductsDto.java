package com.pxc.reactive_flux.categories;

import com.pxc.reactive_flux.products.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryWithProductsDto {
    private Integer id;
    private String name;
    private List<ProductDto> products;
}
