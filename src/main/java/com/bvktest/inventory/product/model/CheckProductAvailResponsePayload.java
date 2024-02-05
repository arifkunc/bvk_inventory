package com.bvktest.inventory.product.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CheckProductAvailResponsePayload {
    private Integer quantity;
}
