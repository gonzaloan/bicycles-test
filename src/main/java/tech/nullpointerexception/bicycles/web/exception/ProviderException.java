package tech.nullpointerexception.bicycles.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProviderException extends RuntimeException {

    public ProviderException(String error) {
        super(error);
    }
}
