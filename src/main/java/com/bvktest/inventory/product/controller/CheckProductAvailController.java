package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.product.model.CheckProductAvailResponsePayload;
import com.bvktest.inventory.product.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class CheckProductAvailController {
    private ProductService productService;

    @Autowired
    public CheckProductAvailController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/inventory/v1/products/{productId}/availability")
    public ResponseEntity<DefaultResponse<CheckProductAvailResponsePayload>> checkProductAvail(@RequestParam Map<String, String> paramMap,
                                                                                               @PathParam("productId") String productId){
        String traceId = paramMap.get("traceId");
        CheckProductAvailResponsePayload responsePayload = execute(productId);

        DefaultResponse<CheckProductAvailResponsePayload> response = DefaultResponse.<CheckProductAvailResponsePayload>builder()
                .traceId(traceId)
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(responsePayload)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private CheckProductAvailResponsePayload execute(String productId){
        int quantity = productService.getProductQuantity(productId);

        return CheckProductAvailResponsePayload.builder()
                .quantity(quantity)
                .build();
    }
}
