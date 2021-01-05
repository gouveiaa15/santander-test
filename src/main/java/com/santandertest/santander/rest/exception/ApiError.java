package com.santandertest.santander.rest.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ApiError {

    private int httpCode;
    private LocalDateTime timeStamp;
    private String customMessage;

    public ApiError(HttpStatus statusError, String customMessage) {
      this.httpCode = statusError.value();
      this.timeStamp = LocalDateTime.now();
      this.customMessage = customMessage;
    }

  public int getHttpCode() { return httpCode; }

  public void setHttpCode(int httpCode) { this.httpCode = httpCode; }

  public LocalDateTime getTimeStamp() { return timeStamp; }

  public void setTimeStamp(LocalDateTime timeStamp) { this.timeStamp = timeStamp; }

  public String getCustomMessage() { return customMessage; }

  public void setCustomMessage(String customMessage) { this.customMessage = customMessage; }

}
