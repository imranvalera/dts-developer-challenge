package uk.gov.hmcts.reform.dev.exception;

import org.springframework.http.HttpStatus;

public class ApiGlobalException extends RuntimeException {

    private final HttpStatus responseHttpStatus;

    public ApiGlobalException(String message, final HttpStatus responseHttpStatus) {
        super(message);
        this.responseHttpStatus = responseHttpStatus;
    }

    public HttpStatus getResponseHttpStatus() {
        return responseHttpStatus;
    }
}
