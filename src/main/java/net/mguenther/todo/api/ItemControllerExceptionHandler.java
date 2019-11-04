package net.mguenther.todo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
public class ItemControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FaultRepr> onException(final MethodArgumentNotValidException cause) {
        final FaultRepr faultRepr = new FaultRepr();
        faultRepr.setDescription(cause.getMessage());
        log.warn("Request failed due to: '{}'", faultRepr.getDescription(), cause);
        return ResponseEntity.badRequest().body(faultRepr);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FaultRepr> onException(final Exception cause) {
        final FaultRepr faultRepr = new FaultRepr();
        faultRepr.setDescription(cause.getMessage());
        log.warn("Request failed due to: '{}'", faultRepr.getDescription(), cause);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(faultRepr);
    }
}
