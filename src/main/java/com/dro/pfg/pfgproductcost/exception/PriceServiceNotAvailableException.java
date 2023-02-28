package com.dro.pfg.pfgproductcost.exception;

public class PriceServiceNotAvailableException extends RuntimeException {

    public PriceServiceNotAvailableException() {
        super("Price service is not available");
    }
}
