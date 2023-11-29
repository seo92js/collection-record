package side.collectionrecord.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class ExRestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResult userExceptionHandler(UserNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("USER_EX", e.getMessage());
    }
}
