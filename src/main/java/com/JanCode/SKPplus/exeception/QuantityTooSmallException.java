package com.JanCode.SKPplus.exeception;

public class QuantityTooSmallException extends Exception {
    public QuantityTooSmallException(String errorMessage) {
        super(errorMessage);
    }
}
