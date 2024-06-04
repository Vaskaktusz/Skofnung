package application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
final class ClientErrorAdvice {
    @ExceptionHandler(RestClientResponseException.class)
    ResponseEntity<String> handle(RestClientResponseException rcre) {
        return new ResponseEntity<>(rcre.getStatusText(), rcre.getStatusCode());
    }

    @ExceptionHandler(RestClientException.class)
    ResponseEntity<String> handle(RestClientException rce) {
        return new ResponseEntity<>(rce.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}