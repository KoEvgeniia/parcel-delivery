package javacourse.exception;

/**
 * Read file exception
 */
public class ReadFileException extends RuntimeException {
    /**
     * Read file exception
     *
     * @param message message with error text
     */
    public ReadFileException(String message) {
        super(message);
    }
}
