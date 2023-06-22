package com.dro.pfg.pfgproductcost.controller;

import com.dro.pfg.pfgproductcost.exception.ApiError;
import com.dro.pfg.pfgproductcost.exception.PriceNotHandledException;
import com.dro.pfg.pfgproductcost.exception.PriceServiceNotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ProductControllerAdvice {

    @ExceptionHandler(PriceNotHandledException.class)
    public ResponseEntity<ApiError> handlePriceNotHandledException (PriceNotHandledException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        log.error("Error due to product not found.", ex);
        return new ResponseEntity<>(new ApiError(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceServiceNotAvailableException.class)
    public ResponseEntity<ApiError> handlePriceServiceNotAvailableException (PriceServiceNotAvailableException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        log.error("Error trying to get price in price-calculator service.", ex);
        return new ResponseEntity<>(new ApiError(errors), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException (Exception ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        log.error("General exception when calling product-cost service.", ex);
        return new ResponseEntity<>(new ApiError(errors), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
