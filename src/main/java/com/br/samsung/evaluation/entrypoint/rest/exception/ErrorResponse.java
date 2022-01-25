package com.br.samsung.evaluation.entrypoint.rest.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private final Integer statusCode;
    private final String message;
    private String detail;
}
