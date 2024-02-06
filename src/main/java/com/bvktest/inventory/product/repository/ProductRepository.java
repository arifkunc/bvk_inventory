package com.bvktest.inventory.product.repository;

import com.bvktest.inventory.product.object.AddProductDto;

public interface ProductRepository {
    int insertProduct(AddProductDto addProductDto);
    int updateQuantity(String productId, int quantityDelta);
    Integer getQuantity(String productId);
}
