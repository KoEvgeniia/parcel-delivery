package javacourse.exception;

/**
 * Exception occurred when entering command errors Load
 */
public class LoadCommandIncorrectException extends RuntimeException {
    /**
     * Exception occurred when entering command errors Load
     * @param message message with error text
     */
    public LoadCommandIncorrectException(String message) {
        super(message);
    }
}
