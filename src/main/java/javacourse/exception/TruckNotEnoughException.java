package javacourse.exception;

public class TruckNotEnoughException extends RuntimeException {
    public TruckNotEnoughException(Long truckCount) {
        super(truckCount + " trucks aren't enough to load all the parcels");
    }
}
