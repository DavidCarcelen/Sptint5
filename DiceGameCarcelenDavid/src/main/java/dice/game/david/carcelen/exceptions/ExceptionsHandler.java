package dice.game.david.carcelen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handlerIdNotFoundException(IdNotFoundException e, WebRequest request) {
        String message = e.getMessage() + " " + request.getDescription(false);

        return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameNotAvailableException.class)
    public ResponseEntity<String> handlerNameNotAvailableException(NameNotAvailableException e, WebRequest request) {
        String message = e.getMessage() + " " + request.getDescription(false);

        return new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE);
    }


}
