package com.bvktest.inventory.product.repository;

import com.bvktest.inventory.product.object.AddProductDto;

public interface ProductRepository {
    /**
     * @param addProductDto
     * @return product id of added product
     */
    String insertProduct(AddProductDto addProductDto);
    int updateQuantity(String productId, int quantityDelta);
    Integer getQuantity(String productId);
}
