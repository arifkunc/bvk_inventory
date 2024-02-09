package com.bvktest.inventory.common.advice;

import com.bvktest.inventory.common.constant.ErrorType;
import com.bvktest.inventory.common.exception.InsufficientStockException;
import com.bvktest.inventory.common.exception.InvalidRequestException;
import com.bvktest.inventory.common.model.Error;
import com.bvktest.inventory.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class InventoryResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(Exception exception, WebRequest webRequest){
        InvalidRequestException invalidRequestException = (InvalidRequestException) exception;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli())) // the value should be same with traceId request. This is just simple set
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .error(
                        Error.builder()
                                .code(ErrorType.INVALID_REQUEST.toString())
                                .message(invalidRequestException.getMessage())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({InsufficientStockException.class})
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(Exception exception, WebRequest webRequest){
        InsufficientStockException insufficientStockException = (InsufficientStockException) exception;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli())) // the value should be same with traceId request. This is just simple set
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .error(
                        Error.builder()
                                .code(ErrorType.INSUFFICIENT_STOCK.toString())
                                .message(insufficientStockException.getMessage())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleDefaultException(Exception exception, WebRequest webRequest){

        ErrorResponse errorResponse = ErrorResponse.builder()
                .traceId(String.valueOf(Instant.now().toEpochMilli())) // the value should be same with traceId request. This is just simple set
                .time(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()))
                .error(
                        Error.builder()
                                .code(ErrorType.INTERNAL_ERROR.toString())
                                .message(exception.getMessage())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
