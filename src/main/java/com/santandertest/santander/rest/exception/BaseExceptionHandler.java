package com.santandertest.santander.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

  private HttpHeaders headers = new HttpHeaders();

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handle() {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Failed to process the request.");

    return new ResponseEntity<>(apiError, headers, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
