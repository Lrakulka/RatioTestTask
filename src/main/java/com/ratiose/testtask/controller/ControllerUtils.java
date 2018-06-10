package com.ratiose.testtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

final class ControllerUtils {
    static ResponseEntity getReturnStatus(boolean result) {
        return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(null);
    }
}
