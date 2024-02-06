package com.bvktest.inventory.product.controller;

import com.bvktest.inventory.common.model.DefaultResponse;
import com.bvktest.inventory.common.model.SuccessResponsePayload;
import com.bvktest.inventory.product.model.UpdateProductQuantityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class UpdateProductQuantityController {
    @PatchMapping("/inventory/v1/products/{productId}/quantity")
    public ResponseEntity<DefaultResponse<SuccessResponsePayload>> updateProductQuantity(@RequestBody UpdateProductQuantityRequest request){
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
