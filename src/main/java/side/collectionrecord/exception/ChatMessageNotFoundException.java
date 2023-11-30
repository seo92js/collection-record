package side.collectionrecord.exception;

public class ChatMessageNotFoundException extends RuntimeException{
    public ChatMessageNotFoundException() {
        super();
    }

    public ChatMessageNotFoundException(String message) {
        super(message);
    }

    public ChatMessageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatMessageNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ChatMessageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
