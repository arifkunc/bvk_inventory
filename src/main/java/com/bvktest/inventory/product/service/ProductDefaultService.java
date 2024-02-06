package com.bvktest.inventory.product.service;

import com.bvktest.inventory.product.object.AddProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductDefaultService implements ProductService{
    @Override
    public void addProduct(AddProductDto addProductDto) {

    }

    @Override
    public int getProductQuantity(String productId) {
        return 0;
    }

    @Override
    public void updateProductQuantity(String productId, int quantityDelta) {

    }
}
