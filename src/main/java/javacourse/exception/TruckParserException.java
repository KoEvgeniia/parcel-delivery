package javacourse.exception;

/**
 * Truck parsing exception
 */
public class TruckParserException extends RuntimeException {
    /**
     * Truck parsing exception
     *
     * @param message message with error text
     */
    public TruckParserException(String message) {
        super(message);
    }
}
