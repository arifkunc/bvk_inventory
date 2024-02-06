package com.bvktest.inventory.common.exception;

public class InsufficientStockException extends RuntimeException{
    private int currentStock;
    private int reductionStock;

    public InsufficientStockException(String message, int currentStock, int reductionStock) {
        super(message);
        this.currentStock = currentStock;
        this.reductionStock = reductionStock;
    }
}
