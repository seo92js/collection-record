package side.collectionrecord.exception;

public class PostsNotFoundException extends RuntimeException{
    public PostsNotFoundException() {
        super();
    }

    public PostsNotFoundException(String message) {
        super(message);
    }

    public PostsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostsNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PostsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
