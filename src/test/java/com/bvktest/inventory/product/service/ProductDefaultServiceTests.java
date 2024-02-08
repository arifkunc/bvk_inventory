package com.bvktest.inventory.product.service;

import com.bvktest.inventory.product.object.AddProductDto;
import com.bvktest.inventory.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductDefaultServiceTests {

    private ProductRepository productRepository;

    @BeforeEach
    public void init(){
        productRepository = Mockito.mock(ProductRepository.class);
    }

    @Test
    public void testAddProductGetProductId(){
        String expectedProductId = "000001";

        Mockito.when(productRepository.insertProduct(Mockito.any(AddProductDto.class)))
                .thenReturn(expectedProductId);

        ProductService productService = new ProductDefaultService(productRepository);

        String newProductId = productService.addProduct(AddProductDto.builder().build());

        Assertions.assertEquals(expectedProductId, newProductId);
    }
}
