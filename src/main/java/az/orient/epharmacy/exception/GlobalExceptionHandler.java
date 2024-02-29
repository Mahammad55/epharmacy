package az.orient.epharmacy.exception;

import az.orient.epharmacy.dto.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PharmacyException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(PharmacyException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code(ex.getStatus().value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus())
                .build();
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotFoundExceptionHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(Message.VALIDATION_EXCEPTION);

        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(message)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(Message.INTERNAL_SERVER_EXCEPTION)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
