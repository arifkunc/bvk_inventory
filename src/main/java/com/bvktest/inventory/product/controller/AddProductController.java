package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.common.model.SuccessResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AddProductController {
    @PostMapping("/inventory/v1/products")
    public ResponseEntity<DefaultResponse<SuccessResponsePayload>> addProduct(){
        // dummy
        DefaultResponse<SuccessResponsePayload> response = DefaultResponse.<SuccessResponsePayload>builder()
                .traceId("abc")
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .data(
                        SuccessResponsePayload.builder()
                                .status("success")
                                .message("process is successfully done")
                                .build()
                )
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
