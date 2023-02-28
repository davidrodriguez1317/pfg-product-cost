package com.dro.pfg.pfgproductcost.controller;

import com.dro.pfg.pfgproductcost.exception.ApiError;
import com.dro.pfg.pfgproductcost.exception.PriceNotHandledException;
import com.dro.pfg.pfgproductcost.exception.PriceServiceNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(PriceNotHandledException.class)
    public ResponseEntity<ApiError> handlePriceNotHandledException (PriceNotHandledException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());

        return new ResponseEntity<>(new ApiError(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceServiceNotAvailableException.class)
    public ResponseEntity<ApiError> handlePriceServiceNotAvailableException (PriceServiceNotAvailableException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());

        return new ResponseEntity<>(new ApiError(errors), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlePGenericException (Exception ex){
        List<String> errors = Collections.singletonList(ex.getMessage());

        return new ResponseEntity<>(new ApiError(errors), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
