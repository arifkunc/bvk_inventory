package com.bvktest.inventory.product.service;

import com.bvktest.inventory.product.object.AddProductDto;

public interface ProductService {
    void addProduct(AddProductDto addProductDto);
    int getProductQuantity(String productId);
    void updateProductQuantity(String productId, int quantityDelta);
}
