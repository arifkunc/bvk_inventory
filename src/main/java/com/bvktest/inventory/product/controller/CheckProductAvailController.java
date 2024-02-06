package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.product.model.CheckProductAvailResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CheckProductAvailController {
    @GetMapping("/inventory/v1/products/{productId}/availability")
    public ResponseEntity<DefaultResponse<CheckProductAvailResponsePayload>> checkProductAvail(){
        // dummy
        DefaultResponse<CheckProductAvailResponsePayload> response = DefaultResponse.<CheckProductAvailResponsePayload>builder()
                .traceId("abc")
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(
                        CheckProductAvailResponsePayload.builder()
                                .quantity(7)
                                .build()
                )
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
