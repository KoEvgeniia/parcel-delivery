package javacourse.exception;

/**
 * Exception when the parcel already exists
 */
public class ParcelAlreadyExistsException extends RuntimeException {
    /**
     * Exception when the parcel already exists
     * @param name name of an existing parcel
     */
    public ParcelAlreadyExistsException(String name) {
        super("Parcel " + name + " already exists");
    }
}
