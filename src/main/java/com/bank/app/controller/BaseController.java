package com.bank.app.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;

import static com.bank.app.utils.AppConstant.*;

public abstract class BaseController {
    public ResponseEntity<?> getResponse(String statusType, String message, Integer status) {
        if (statusType.equals(ERROR))
            return ResponseEntity.status(status).body(
                    ImmutableMap.of(statusType, ImmutableMap.of(STATUS, status, MESSAGE, message)));

        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS, status, STATUS_TYPE, statusType, MESSAGE, message));

    }
}
