package com.product.GlobalExceptionHandler;


import com.product.ApiResponse.ApiResponse;
import com.product.Exceptions.ProductServiceCustomException;
import com.product.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse ResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse apiResponse=new ApiResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(ProductServiceCustomException.class)
    public ApiResponse ProductServiceCustomException(ProductServiceCustomException ex){

        ApiResponse apiResponse=new ApiResponse(HttpStatus.NOT_FOUND,ex.getMessage());
        return apiResponse;
    }



}
