package selenium.logger;

/**
 * Created by alexander.
 */
public class UnloggingException extends RuntimeException {

    public UnloggingException() {
        super();
    }

    public UnloggingException(String message) {
        super(message);
    }

    public UnloggingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnloggingException(Throwable cause) {
        super(cause);
    }
}
