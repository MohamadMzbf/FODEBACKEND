package com.fodev2.backendv2Fode.services;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}