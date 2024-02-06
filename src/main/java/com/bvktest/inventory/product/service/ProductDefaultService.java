package com.bvktest.inventory.product.service;

import com.bvktest.inventory.common.exception.InsufficientStockException;
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
    public String addProduct(AddProductDto addProductDto) {
        String productId = productRepository.insertProduct(addProductDto);

        return productId;
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
        if(quantityDelta > 0){
            productRepository.updateQuantity(productId, quantityDelta);
        } else if(quantityDelta < 0){
            int currentQuantity = productRepository.getQuantity(productId);
            if(currentQuantity + quantityDelta < 0){
                throw new InsufficientStockException("There is an insufficient stock", currentQuantity, Math.abs(quantityDelta));
            } else {
                productRepository.updateQuantity(productId, quantityDelta);
            }
        } else {
            throw new IllegalArgumentException("Can't update quantity with 0");
        }
    }
}
