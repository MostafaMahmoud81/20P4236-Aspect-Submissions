package com.example.lab4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class LockAcquisition extends RuntimeException {
    public LockAcquisition(String msg) {
        super(msg);
    }
}