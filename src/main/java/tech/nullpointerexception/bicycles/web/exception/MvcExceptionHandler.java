package tech.nullpointerexception.bicycles.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.nullpointerexception.bicycles.util.UtilConstants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestController
@Slf4j
public class MvcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse handleConstraintsError(ConstraintViolationException e) {
        log.error("Se ha generado error por constraints {} ", e.getConstraintViolations());

        List<String> errorDetails = e.getConstraintViolations().isEmpty() ? Collections.singletonList(UtilConstants.ERROR_GETTING_DATA) :
                e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(UtilConstants.ERROR_MESSAGE)
                .details(errorDetails)
                .build();

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public ErrorResponse handleExceptionNotFound() {
        return ErrorResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .code(NOT_FOUND.value())
                .message(UtilConstants.ERROR_MESSAGE)
                .details(Collections.singletonList(UtilConstants.NOT_FOUND_DATA))
                .build();
    }

    /**
     * Controlador para errores generales del servidor referentes al provider.
     */
    @ExceptionHandler(ProviderException.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse handleExceptionGeneralProvider(ProviderException e) {
        return ErrorResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .code(INTERNAL_SERVER_ERROR.value())
                .message(UtilConstants.ERROR_MESSAGE)
                .details(Collections.singletonList(e.getMessage()))
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("Se ha generado un error. handleMethodArgumentNotValid.-");
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .code(BAD_REQUEST.value())
                .message(UtilConstants.ERROR_MESSAGE)
                .details(details)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
