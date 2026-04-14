package javacourse.exception;

/**
 * Parcel or truck process exception
 */
public class ProcessCommandException extends RuntimeException {
    /**
     * Parcel or truck process exception
     *
     * @param message message with error text
     */
    public ProcessCommandException(String message) {
        super(message);
    }
}
