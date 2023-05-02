package application.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public final class ClientError {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handle(HttpClientErrorException cee) {
        return new ResponseEntity<>(cee.getStatusText(), cee.getStatusCode());
    }
}