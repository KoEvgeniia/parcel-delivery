package javacourse.exception;

/**
 * Exception when there are not enough trucks to load
 */
public class TruckNotEnoughException extends RuntimeException {
    /**
     * Exception when there are not enough trucks to load
     * @param truckCount number of trucks to load
     */
    public TruckNotEnoughException(Long truckCount) {
        super(truckCount + " trucks aren't enough to load all the parcels");
    }
}
