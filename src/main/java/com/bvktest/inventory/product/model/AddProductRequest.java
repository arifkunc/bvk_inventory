package com.bvktest.inventory.product.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddProductRequest {
    private String traceId;
    private String name;
    private Double price;
    private Integer quantity;
}
