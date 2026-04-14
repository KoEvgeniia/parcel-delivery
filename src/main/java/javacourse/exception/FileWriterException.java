package javacourse.exception;

/**
 * File write exception
 */
public class FileWriterException extends RuntimeException {
    /**
     * File write exception
     *
     * @param message message with error text
     */
    public FileWriterException(String message) {
        super(message);
    }
}
