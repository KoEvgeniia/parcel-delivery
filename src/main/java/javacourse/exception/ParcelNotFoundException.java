package javacourse.exception;

/**
 * Exception when the parcel is not found
 */
public class ParcelNotFoundException extends RuntimeException {
    /**
     * Exception when the parcel is not found
     * @param name name of a parcel
     */
    public ParcelNotFoundException(String name) {
        super("Parcel not found: " + name);
    }
}
