package com.bvktest.inventory.product.service;

import com.bvktest.inventory.product.object.AddProductDto;

public interface ProductService {
    String addProduct(AddProductDto addProductDto);
    Integer getProductQuantity(String productId);
    void updateProductQuantity(String productId, int quantityDelta);
}
