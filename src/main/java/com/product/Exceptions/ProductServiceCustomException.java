package com.product.Exceptions;

public class ProductServiceCustomException extends RuntimeException {

    public ProductServiceCustomException(String message) {
        super(message);
    }
}
