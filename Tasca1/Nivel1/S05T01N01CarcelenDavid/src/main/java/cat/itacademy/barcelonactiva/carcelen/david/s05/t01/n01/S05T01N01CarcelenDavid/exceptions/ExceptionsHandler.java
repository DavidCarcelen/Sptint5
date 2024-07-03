package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<String> handleBranchNotFoundException(BranchNotFoundException e, WebRequest request) {
        String message = e.getMessage() + " " + request.getDescription(false);

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
