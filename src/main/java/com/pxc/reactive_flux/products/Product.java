package com.pxc.reactive_flux.products;

import com.pxc.reactive_flux.categories.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @Column(value = "id")
    private Integer id;

    @Column(value = "category_id")
    private Integer categoryId;

    @Column(value = "name")
    private String name;

    @Column(value = "price")
    private BigDecimal price;

    @Transient
    private Category category;
}
