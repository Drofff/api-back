package ua.ozzy.apiback.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.ozzy.apiback.dto.FieldErrorsDto;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.exception.AuthorizationException;
import ua.ozzy.apiback.exception.FieldErrorsException;
import ua.ozzy.apiback.exception.ValidationException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class GlobalExceptionsHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

    @ExceptionHandler
    public ResponseEntity<MessageDto> handleAuthorizationException(AuthorizationException e) {
        String msg = e.getMessage();
        return status(UNAUTHORIZED).body(new MessageDto(msg));
    }

    @ExceptionHandler
    public ResponseEntity<MessageDto> handleValidationException(ValidationException e) {
        String msg = e.getMessage();
        return badRequest().body(new MessageDto(msg));
    }

    @ExceptionHandler
    public ResponseEntity<FieldErrorsDto> handleFieldErrorsException(FieldErrorsException e) {
        FieldErrorsDto dto = new FieldErrorsDto(e.getFieldErrors());
        return badRequest().body(dto);
    }

    @ExceptionHandler
    public ResponseEntity<MessageDto> handleApiBackException(ApiBackException e) {
        String msg = e.getMessage();
        if (e.getCause() != null) {
            Throwable cause = e.getCause();
            LOG.error("Unexpected server error: ", cause);
            msg = cause.getMessage();
        }
        return status(INTERNAL_SERVER_ERROR).body(new MessageDto(msg));
    }

}
