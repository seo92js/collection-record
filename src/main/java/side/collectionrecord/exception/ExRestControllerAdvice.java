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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PostsNotFoundException.class)
    public ErrorResult postsExceptionHandler(PostsNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("POSTS_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotificationNotFoundException.class)
    public ErrorResult notificationExceptionHandler(NotificationNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("NOTIFICATION_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageNotFoundException.class)
    public ErrorResult imageExceptionHandler(ImageNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("IMAGE_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentNotFoundException.class)
    public ErrorResult commentExceptionHandler(CommentNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("COMMENT_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChatRoomNotFoundException.class)
    public ErrorResult chatRoomExceptionHandler(ChatRoomNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("CHATROOM_EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChatMessageNotFoundException.class)
    public ErrorResult chatMessageExceptionHandler(ChatMessageNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("CHATMESSAGE_EX", e.getMessage());
    }
}
