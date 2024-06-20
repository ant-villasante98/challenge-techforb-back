package com.techforb.techforb_webapi.webapi.Filters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techforb.techforb_webapi.core.dtos.UnsuccessResponse;
import com.techforb.techforb_webapi.core.exceptions.AlreadyExistsException;
import com.techforb.techforb_webapi.core.exceptions.UnauthorizedException;

import io.micrometer.common.lang.Nullable;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AlreadyExistsException.class })
    public ResponseEntity<Object> alreadyExistsHandle(Exception ex, WebRequest request) {
        return customResponse(ex.getMessage(), ex, List.of(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ AuthorizationDeniedException.class, UnauthorizedException.class })
    public ResponseEntity<Object> unauthorizationHandle(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
    }

    @ExceptionHandler({ RuntimeException.class })
    public Object defaultHandle(RuntimeException ex, WebRequest request) {
        ex.getClass();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        UnsuccessResponse response = new UnsuccessResponse(ex.getMessage(),
                status.value(), status.getReasonPhrase(), null);
        return new ResponseEntity<Object>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return this.customResponse("Uno o mas argumentos invalidos", ex, ex.getBindingResult().getAllErrors(),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> customResponse(String title, Exception ex, @Nullable List<ObjectError> details,
            HttpStatus status) {
        Map<String, String> errors = new HashMap<>();
        if (details != null) {
            details.forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        UnsuccessResponse response = new UnsuccessResponse(title, status.value(), status.getReasonPhrase(), errors);
        return new ResponseEntity<>(response, status);
    }
}
