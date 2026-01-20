package com.pxc.reactive_flux.categories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @Column(value = "id")
    private Integer id;

    @Column(value = "name")
    private String name;
}
