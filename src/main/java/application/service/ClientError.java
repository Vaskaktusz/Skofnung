package application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public final class ClientError {
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handle(RestClientResponseException rce) {
        return new ResponseEntity<>(rce.getStatusText(), rce.getStatusCode());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handle(RestClientException rae) {
        return new ResponseEntity<>(rae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}