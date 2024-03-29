package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.common.model.SuccessResponsePayload;
import com.bvktest.inventory.product.model.UpdateProductQuantityRequest;
import com.bvktest.inventory.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class UpdateProductQuantityController {
    private ProductService productService;

    @Autowired
    public UpdateProductQuantityController(ProductService productService) {
        this.productService = productService;
    }

    @PatchMapping("/inventory/v1/products/{productId}/quantity")
    public ResponseEntity<DefaultResponse<SuccessResponsePayload>> updateProductQuantity(@PathVariable("productId") String productId,
            @RequestBody UpdateProductQuantityRequest request){

        SuccessResponsePayload responsePayload = execute(productId, request);

        DefaultResponse<SuccessResponsePayload> response = DefaultResponse.<SuccessResponsePayload>builder()
                .traceId(request.getTraceId())
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(responsePayload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private SuccessResponsePayload execute(String productId, UpdateProductQuantityRequest request){
        productService.updateProductQuantity(productId, request.getQuantityDelta());

        return SuccessResponsePayload.builder()
                .status("success")
                .message("process is successfully done")
                .build();
    }
}
