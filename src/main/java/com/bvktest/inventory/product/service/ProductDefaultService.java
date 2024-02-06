package com.bvktest.inventory.product.service;

import com.bvktest.inventory.product.object.AddProductDto;
import com.bvktest.inventory.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDefaultService implements ProductService{
    private ProductRepository productRepository;

    @Autowired
    public ProductDefaultService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void addProduct(AddProductDto addProductDto) {
        productRepository.insertProduct(addProductDto);
    }

    @Override
    public Integer getProductQuantity(String productId) {
        return productRepository.getQuantity(productId);
    }

    @Override
    @Transactional
    public void updateProductQuantity(String productId, int quantityDelta) {
        /*
        In ideal case, it's better if we implement update lock mechanism in this part to avoid race condition for update quantity
        But in case of test of making project, just do simple implementation
        */
        productRepository.updateQuantity(productId, quantityDelta);
    }
}
