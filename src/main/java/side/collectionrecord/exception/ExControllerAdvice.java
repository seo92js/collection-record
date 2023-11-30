package side.collectionrecord.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public String userExceptionHandler(UserNotFoundException e, Model model) {
        log.error("[exceptionHandler] ex", e);
        model.addAttribute("message", e.getMessage());
        return "error/400";
    }

    @ExceptionHandler(PostsNotFoundException.class)
    public String postsExceptionHandler(PostsNotFoundException e, Model model) {
        log.error("[exceptionHandler] ex", e);
        model.addAttribute("message", e.getMessage());
        return "error/400";
    }
}
