package com.bvktest.inventory.product.object;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddProductDto {
    private String name;
    private Double price;
    private Integer quantity;
}
