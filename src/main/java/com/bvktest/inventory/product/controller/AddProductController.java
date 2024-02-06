package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.product.model.AddProductRequest;
import com.bvktest.inventory.product.model.AddProductResponsePayload;
import com.bvktest.inventory.product.object.AddProductDto;
import com.bvktest.inventory.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AddProductController {
    private ProductService productService;

    @Autowired
    public AddProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/inventory/v1/products")
    public ResponseEntity<DefaultResponse<AddProductResponsePayload>> addProduct(@RequestBody AddProductRequest request){
        AddProductResponsePayload responsePayload = execute(request);

        DefaultResponse<AddProductResponsePayload> response = DefaultResponse.<AddProductResponsePayload>builder()
                .traceId(request.getTraceId())
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(responsePayload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private AddProductResponsePayload execute(AddProductRequest request){
        AddProductDto addProductDto = AddProductDto.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        String productId = productService.addProduct(addProductDto);

        return AddProductResponsePayload.builder()
                .productId(productId)
                .build();
    }
}
