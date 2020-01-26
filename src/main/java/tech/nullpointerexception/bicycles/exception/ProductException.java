package tech.nullpointerexception.bicycles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductException extends RuntimeException {

    public ProductException(String error) {
        super(error);
    }
}
